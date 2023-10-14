package com.example.demo.participant.function;

import com.example.demo.participant.dto.GetParticipantResponse;
import com.example.demo.participant.entity.Participant;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class ParticipantToResponseFunction implements Function<Participant, GetParticipantResponse> {
    @Override
    public GetParticipantResponse apply(Participant participant) {
        return GetParticipantResponse.builder()
                .id(participant.getId())
                .surname(participant.getSurname())
                .rank(participant.getRank())
                .tournament(GetParticipantResponse.Tournament.builder()
                        .id(participant.getTournament().getId())
                        .name(participant.getTournament().getName())
                        .requiredRanking(participant.getTournament().getRequiredRank())
                        .build())
                .build();
    }
}
