package pg.tournament.controller.impl;

import pg.tournament.controller.api.TournamentController;
import pg.tournament.dto.GetTournamentResponse;
import pg.tournament.dto.GetTournamentsResponse;
import pg.tournament.dto.PatchTournamentRequest;
import pg.tournament.dto.PutTournamentRequest;
import pg.tournament.entity.Tournament;
import pg.tournament.function.RequestToTournamentFunction;
import pg.tournament.function.RequestToTournamentPatchFunction;
import pg.tournament.function.TournamentToResponseFunction;
import pg.tournament.function.TournamentsToResponseFunction;
import pg.tournament.service.api.TournamentService;
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
    private final RequestToTournamentPatchFunction requestToTournamentPatch;
    @Autowired
    public TournamentDefaultController(
            TournamentService service,
            TournamentToResponseFunction tournamentToResponse,
            TournamentsToResponseFunction tournamentsToResponse,
            RequestToTournamentFunction requestToTournament,
            RequestToTournamentPatchFunction requestToTournamentPatch
    ){
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
