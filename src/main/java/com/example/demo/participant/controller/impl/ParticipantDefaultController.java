package com.example.demo.participant.controller.impl;

import com.example.demo.participant.controller.api.ParticipantController;
import com.example.demo.participant.dto.GetParticipantResponse;
import com.example.demo.participant.dto.GetParticipantsResponse;
import com.example.demo.participant.dto.PutParticipantRequest;
import com.example.demo.participant.function.ParticipantToResponseFunction;
import com.example.demo.participant.function.ParticipantsToResponseFunction;
import com.example.demo.participant.function.RequestToParticipantFunction;
import com.example.demo.participant.service.api.ParticipantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@RestController
public class ParticipantDefaultController implements ParticipantController {
    private final ParticipantService service;
    private final ParticipantToResponseFunction participantToResponse;
    private final ParticipantsToResponseFunction participantsToResponse;
    private final RequestToParticipantFunction requestToParticipant;

    @Autowired
    public ParticipantDefaultController(
            ParticipantService service,
            ParticipantToResponseFunction participantToResponse,
            ParticipantsToResponseFunction participantsToResponse,
            RequestToParticipantFunction requestToParticipant
    ){
        this.service = service;
        this.participantsToResponse = participantsToResponse;
        this.participantToResponse = participantToResponse;
        this.requestToParticipant = requestToParticipant;
    }

    @Override
    public GetParticipantsResponse getParticipants() {
        return participantsToResponse.apply(service.findAll());
    }

    @Override
    public GetParticipantResponse getParticipant(UUID id) {
        return service.find(id)
                .map(participantToResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public GetParticipantsResponse getTournamentParticipants(UUID tournamentId) {
        return service.findAllByTournament(tournamentId)
                .map(participantsToResponse)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void putParticipant(UUID id, PutParticipantRequest request) {
        service.create(requestToParticipant.apply(id, request));
    }

    @Override
    public void deleteParticipant(UUID id) {
        service.find(id)
                .ifPresentOrElse(
                        participant -> service.delete(id),
                        () -> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
                        }
                );
    }
}
