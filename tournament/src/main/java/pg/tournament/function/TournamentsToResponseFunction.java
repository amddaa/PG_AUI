package pg.tournament.function;

import pg.tournament.dto.GetTournamentsResponse;
import pg.tournament.entity.Tournament;
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
