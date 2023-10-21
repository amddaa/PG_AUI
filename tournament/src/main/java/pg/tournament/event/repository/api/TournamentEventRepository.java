package pg.tournament.event.repository.api;

import pg.tournament.dto.PatchTournamentRequest;
import pg.tournament.dto.PutTournamentRequest;
import pg.tournament.entity.Tournament;

import java.util.UUID;

public interface TournamentEventRepository {
    void delete(UUID id);

    void create(PutTournamentRequest request);
}
