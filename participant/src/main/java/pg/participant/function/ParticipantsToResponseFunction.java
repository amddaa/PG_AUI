package pg.participant.function;

import pg.participant.dto.GetParticipantsResponse;
import pg.participant.entity.Participant;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class ParticipantsToResponseFunction implements Function<List<Participant>, GetParticipantsResponse> {
    @Override
    public GetParticipantsResponse apply(List<Participant> participants) {
        return GetParticipantsResponse.builder()
                .participants(participants.stream()
                        .map(participant -> GetParticipantsResponse.Participant.builder()
                                .id(participant.getId())
                                .surname(participant.getSurname())
                                .rank(participant.getRank())
                                .build())
                        .toList())
                .build();
    }
}
