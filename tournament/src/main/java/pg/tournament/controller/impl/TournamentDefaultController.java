package pg.tournament.controller.impl;

import pg.tournament.controller.api.TournamentController;
import pg.tournament.dto.GetTournamentResponse;
import pg.tournament.dto.GetTournamentsResponse;
import pg.tournament.dto.PutTournamentRequest;
import pg.tournament.function.RequestToTournamentFunction;
import pg.tournament.function.TournamentToResponseFunction;
import pg.tournament.function.TournamentsToResponseFunction;
import pg.tournament.service.api.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
public class TournamentDefaultController implements TournamentController {
    private final TournamentService service;
    private final TournamentToResponseFunction tournamentToResponse;
    private final TournamentsToResponseFunction tournamentsToResponse;
    private final RequestToTournamentFunction requestToTournament;
    @Autowired
    public TournamentDefaultController(
            TournamentService service,
            TournamentToResponseFunction tournamentToResponse,
            TournamentsToResponseFunction tournamentsToResponse,
            RequestToTournamentFunction requestToTournament
    ){
        this.service=service;
        this.tournamentsToResponse=tournamentsToResponse;
        this.tournamentToResponse=tournamentToResponse;
        this.requestToTournament=requestToTournament;
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
