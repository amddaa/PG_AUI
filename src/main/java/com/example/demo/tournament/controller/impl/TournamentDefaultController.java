package com.example.demo.tournament.controller.impl;

import com.example.demo.tournament.controller.api.TournamentController;
import com.example.demo.tournament.dto.GetTournamentResponse;
import com.example.demo.tournament.dto.GetTournamentsResponse;
import com.example.demo.tournament.dto.PatchTournamentRequest;
import com.example.demo.tournament.dto.PutTournamentRequest;
import com.example.demo.tournament.entity.Tournament;
import com.example.demo.tournament.function.RequestToTournamentFunction;
import com.example.demo.tournament.function.RequestToTournamentPatchFunction;
import com.example.demo.tournament.function.TournamentToResponseFunction;
import com.example.demo.tournament.function.TournamentsToResponseFunction;
import com.example.demo.tournament.service.api.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@RestController
public class TournamentDefaultController implements TournamentController {
    private final TournamentService service;
    private final TournamentToResponseFunction tournamentToResponse;
    private final TournamentsToResponseFunction tournamentsToResponse;
    private final RequestToTournamentFunction requestToTournament;
    private final RequestToTournamentPatchFunction  requestToTournamentPatch;
    @Autowired
    public TournamentDefaultController(
            TournamentService service,
            TournamentToResponseFunction tournamentToResponse,
            TournamentsToResponseFunction tournamentsToResponse,
            RequestToTournamentFunction requestToTournament,
            RequestToTournamentPatchFunction requestToTournamentPatch){
        this.service=service;
        this.tournamentsToResponse=tournamentsToResponse;
        this.tournamentToResponse=tournamentToResponse;
        this.requestToTournament=requestToTournament;
        this.requestToTournamentPatch = requestToTournamentPatch;
    }

    @Override
    public GetTournamentsResponse getTournaments() {
        return tournamentsToResponse.apply(service.findAll());
    }

    @Override
    public GetTournamentResponse getTournament(UUID id) {
        return service.find(id)
                .map(tournamentToResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void patchTournament(UUID id, PatchTournamentRequest request) {
        Optional<Tournament> existing = service.find(id);
        if(existing.isPresent())
        {
            if(request.getRequiredRank()==0) request.setRequiredRank(existing.get().getRequiredRank());
            if(request.getName()==null) request.setName(existing.get().getName());
        }
        else throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        service.create(requestToTournamentPatch.apply(id, request));
    }

    @Override
    public void putTournament(UUID id, PutTournamentRequest request) {
        service.create(requestToTournament.apply(id, request));
    }

    @Override
    public void deleteTournament(UUID id) {
        service.find(id)
                .ifPresentOrElse(
                        tournament -> service.delete(id),
                        () -> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                        }
                );
    }
}
