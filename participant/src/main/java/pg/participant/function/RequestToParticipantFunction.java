package pg.participant.function;

import pg.participant.dto.PutParticipantRequest;
import pg.participant.entity.Participant;
import pg.tournament.entity.Tournament;
import pg.tournament.service.impl.TournamentDefaultService;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.BiFunction;

@Component
public class RequestToParticipantFunction implements BiFunction<UUID, PutParticipantRequest, Participant> {

    @Override
    public Participant apply(UUID uuid, PutParticipantRequest putParticipantRequest) {
        return Participant.builder()
                .id(uuid)
                .surname(putParticipantRequest.getSurname())
                .rank(putParticipantRequest.getRank())
                .tournament(Tournament.builder() //updated by business logic???
                        .id(putParticipantRequest.getTournament())
                        .build())
                .build();
    }
}
