package pg.tournament.initialize;

import pg.tournament.entity.Tournament;
import pg.tournament.service.api.TournamentService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class InitializeData implements InitializingBean {
    private final TournamentService tournamentService;

    @Autowired
    public InitializeData(TournamentService tournamentService){
        this.tournamentService = tournamentService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Tournament tournament1 = Tournament.builder()
                .id(UUID.fromString("f5875513-bf7b-4ae1-b8a5-5b70a1b90e76"))
                .name("Bardzo wazny turniej")
                .requiredRank(2000)
                .build();

        Tournament tournament2 = Tournament.builder()
                .id(UUID.fromString("8db203fc-152e-426d-81ee-d9bb82123039"))
                .name("Malo wazny turniej")
                .requiredRank(1000)
                .build();

        tournamentService.create(tournament1);
        tournamentService.create(tournament2);
    }
}
