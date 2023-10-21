package pg.tournament.function;

import pg.tournament.dto.PatchTournamentRequest;
import pg.tournament.entity.Tournament;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.BiFunction;

@Component
public class RequestToTournamentPatchFunction implements BiFunction<UUID, PatchTournamentRequest, Tournament> {
    @Override
    public Tournament apply(UUID uuid, PatchTournamentRequest patchTournamentRequest) {
        return Tournament.builder()
                .id(uuid)
                .name(patchTournamentRequest.getName())
                .requiredRank(patchTournamentRequest.getRequiredRank())
                .build();
    }
}
