package pg.participant.function;

import pg.participant.dto.PatchParticipantRequest;
import pg.participant.dto.PutParticipantRequest;
import pg.participant.entity.Participant;
import pg.tournament.entity.Tournament;
import pg.tournament.service.impl.TournamentDefaultService;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.BiFunction;

@Component
public class RequestToParticipantPatchFunction implements BiFunction<UUID, PatchParticipantRequest, Participant> {

    @Override
    public Participant apply(UUID uuid, PatchParticipantRequest patchParticipantRequest) {
        return Participant.builder()
                .id(uuid)
                .surname(patchParticipantRequest.getSurname())
                .rank(patchParticipantRequest.getRank())
                .tournament(Tournament.builder() //updated by business logic???
                        .id(patchParticipantRequest.getTournament())
                        .requiredRank(-1).build())
                .build();
    }
}
