package pg.tournament.event.repository.api;

import pg.tournament.dto.PatchTournamentRequest;
import pg.tournament.dto.PutTournamentRequest;

import java.util.UUID;

public interface TournamentEventRepository {
    void delete(UUID id);
    void update(UUID id, PatchTournamentRequest request);
    void create(UUID id, PutTournamentRequest request);
}
