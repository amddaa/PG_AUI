package pg.tournament.controller.impl;

import pg.tournament.dto.PutTournamentRequest;
import pg.tournament.entity.Tournament;
import pg.tournament.service.api.TournamentService;
import pg.tournament.controller.api.TournamentController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
public class TournamentDefaultController implements TournamentController {
    private final TournamentService service;
    @Autowired
    public TournamentDefaultController(TournamentService service){
        this.service=service;
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

    @Override
    public void putTournament(UUID id, PutTournamentRequest request) {
        service.create(Tournament.builder()
                .name(request.getName())
                .requiredRank(request.getRequiredRank())
                .build());
    }
}
