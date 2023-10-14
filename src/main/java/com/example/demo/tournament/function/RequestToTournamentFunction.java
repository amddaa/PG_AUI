package com.example.demo.tournament.function;

import com.example.demo.tournament.dto.PutTournamentRequest;
import com.example.demo.tournament.entity.Tournament;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.BiFunction;

@Component
public class RequestToTournamentFunction implements BiFunction<UUID, PutTournamentRequest, Tournament> {
    @Override
    public Tournament apply(UUID uuid, PutTournamentRequest putTournamentRequest) {
        return Tournament.builder()
                .id(uuid)
                .name(putTournamentRequest.getName())
                .requiredRank(putTournamentRequest.getRequiredRank())
                .build();
    }
}
