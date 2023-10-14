package com.example.demo.tournament.function;

import com.example.demo.tournament.dto.GetTournamentsResponse;
import com.example.demo.tournament.entity.Tournament;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class TournamentsToResponseFunction implements Function<List<Tournament>, GetTournamentsResponse> {
    @Override
    public GetTournamentsResponse apply(List<Tournament> tournaments) {
        return GetTournamentsResponse.builder()
                .tournaments(tournaments.stream()
                        .map(tournament -> GetTournamentsResponse.Tournament.builder()
                                .id(tournament.getId())
                                .name(tournament.getName())
                                .requiredRank(tournament.getRequiredRank())
                                .build())
                        .toList())
                .build();
    }
}
