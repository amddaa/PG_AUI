package com.example.demo.participant.controller.api;

import com.example.demo.participant.dto.GetParticipantResponse;
import com.example.demo.participant.dto.GetParticipantsResponse;
import com.example.demo.participant.dto.PatchParticipantRequest;
import com.example.demo.participant.dto.PutParticipantRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

public interface ParticipantController {
    @GetMapping("/api/participants")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetParticipantsResponse getParticipants();

    @GetMapping("/api/participants/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetParticipantResponse getParticipant(
            @PathVariable("id")
            UUID id
    );

    @GetMapping("/api/tournaments/{tournamentId}/participants")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    GetParticipantsResponse getTournamentParticipants(
            @PathVariable("tournamentId")
            UUID tournamentId
    );

    @PatchMapping("/api/participants/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    void patchParticipant(
            @PathVariable("id")
            UUID id,
            @RequestBody
            PatchParticipantRequest request
    );

    @PutMapping("/api/participants/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    void putParticipant(
            @PathVariable("id")
            UUID id,
            @RequestBody
            PutParticipantRequest request
    );

    @DeleteMapping("/api/participants/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void deleteParticipant(
            @PathVariable("id")
            UUID id
    );
}
