package pg.participant.intialize;

import pg.participant.entity.Participant;
import pg.participant.service.api.ParticipantService;
import pg.tournament.entity.Tournament;
import pg.tournament.service.api.TournamentService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class InitializeData implements InitializingBean {
    private final TournamentService tournamentService;

    private final ParticipantService participantService;

    @Autowired
    public InitializeData(
            TournamentService tournamentService,
            ParticipantService participantService
    ){
        this.tournamentService = tournamentService;
        this.participantService = participantService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Tournament tournament1 = Tournament.builder()
                .id(UUID.fromString("f5875513-bf7b-4ae1-b8a5-5b70a1b90e76"))
                .name("Bardzo wazny turniej")
                .requiredRank(2000)
                .build();

        Tournament tournament2 = Tournament.builder()
                .id(UUID.randomUUID())
                .name("Malo wazny turniej")
                .requiredRank(1000)
                .build();

        tournamentService.create(tournament1);
        tournamentService.create(tournament2);

        Participant participant1 = Participant.builder()
                .id(UUID.randomUUID())
                .surname("Kowalski")
                .rank(2500)
                .tournament(tournament1)
                .build();

        Participant participant2 = Participant.builder()
                .id(UUID.fromString("525d3e7b-bb1f-4c13-bf17-926d1a12e4c0"))
                .surname("Niekowalski")
                .rank(2300)
                .tournament(tournament1)
                .build();

        Participant participant3 = Participant.builder()
                .id(UUID.randomUUID())
                .surname("Duda")
                .rank(1100)
                .tournament(tournament2)
                .build();

        Participant participant4 = Participant.builder()
                .id(UUID.randomUUID())
                .surname("Polanski")
                .rank(1000)
                .tournament(tournament2)
                .build();

        participantService.create(participant1);
        participantService.create(participant2);
        participantService.create(participant3);
        participantService.create(participant4);
    }
}
