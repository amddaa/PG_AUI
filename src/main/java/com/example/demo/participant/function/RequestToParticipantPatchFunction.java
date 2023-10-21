package com.example.demo.participant.function;

import com.example.demo.participant.dto.PatchParticipantRequest;
import com.example.demo.participant.dto.PutParticipantRequest;
import com.example.demo.participant.entity.Participant;
import com.example.demo.tournament.entity.Tournament;
import com.example.demo.tournament.service.impl.TournamentDefaultService;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.BiFunction;

@Component
public class RequestToParticipantPatchFunction implements BiFunction<UUID, PatchParticipantRequest, Participant> {

    @Override
    public Participant apply(UUID uuid, PatchParticipantRequest patchParticipantRequest) {
        return Participant.builder()
                .id(uuid)
                .surname(patchParticipantRequest.getSurname())
                .rank(patchParticipantRequest.getRank())
                .tournament(Tournament.builder() //updated by business logic???
                        .id(patchParticipantRequest.getTournament())
                        .requiredRank(-1).build())
                .build();
    }
}
