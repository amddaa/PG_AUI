package com.example.demo;

import com.example.demo.participant.Participant;
import com.example.demo.participant.service.api.ParticipantService;
import com.example.demo.tournament.Tournament;
import com.example.demo.tournament.service.api.TournamentService;
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
                .uuid(UUID.randomUUID())
                .name("Bardzo wazny turniej")
                .requiredRanking(2000)
                .build();

        Tournament tournament2 = Tournament.builder()
                .uuid(UUID.randomUUID())
                .name("Malo wazny turniej")
                .requiredRanking(1000)
                .build();

        tournamentService.create(tournament1);
        tournamentService.create(tournament2);

        Participant participant1 = Participant.builder()
                .uuid(UUID.randomUUID())
                .surname("Kowalski")
                .rank(2500)
                .tournament(tournament1)
                .build();

        Participant participant2 = Participant.builder()
                .uuid(UUID.randomUUID())
                .surname("Niekowalski")
                .rank(2300)
                .tournament(tournament1)
                .build();

        Participant participant3 = Participant.builder()
                .uuid(UUID.randomUUID())
                .surname("Duda")
                .rank(1100)
                .tournament(tournament2)
                .build();

        Participant participant4 = Participant.builder()
                .uuid(UUID.randomUUID())
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
