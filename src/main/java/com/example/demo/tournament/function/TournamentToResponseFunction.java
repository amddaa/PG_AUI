package com.example.demo.tournament.function;

import com.example.demo.tournament.dto.GetTournamentResponse;
import com.example.demo.tournament.entity.Tournament;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class TournamentToResponseFunction implements Function<Tournament, GetTournamentResponse> {
    @Override
    public GetTournamentResponse apply(Tournament tournament) {
        return GetTournamentResponse.builder()
                .id(tournament.getId())
                .name(tournament.getName())
                .requiredRank(tournament.getRequiredRank())
                .build();
    }
}
