package com.example.demo.tournament.controller.api;

import com.example.demo.tournament.dto.GetTournamentResponse;
import com.example.demo.tournament.dto.GetTournamentsResponse;
import com.example.demo.tournament.dto.PatchTournamentRequest;
import com.example.demo.tournament.dto.PutTournamentRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public interface TournamentController {
    @GetMapping("/api/tournaments")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetTournamentsResponse getTournaments();

    @GetMapping("/api/tournaments/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetTournamentResponse getTournament(
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

    @DeleteMapping("/api/tournaments/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteTournament(
            @PathVariable("id")
            UUID id
    );
}
