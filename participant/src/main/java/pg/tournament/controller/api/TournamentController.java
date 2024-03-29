package pg.tournament.controller.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pg.tournament.dto.PatchTournamentRequest;
import pg.tournament.dto.PutTournamentRequest;

import java.util.UUID;

public interface TournamentController {

    @DeleteMapping("/api/tournaments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteTournament(
            @PathVariable("id")
            UUID id
    );

    @PatchMapping("/api/tournaments/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    void patchTournament(
            @PathVariable("id")
            UUID id,
            @RequestBody
            PatchTournamentRequest request
    );

    @PutMapping("/api/tournaments/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    void putTournament(
            @PathVariable("id")
            UUID id,
            @RequestBody
            PutTournamentRequest request
    );
}
