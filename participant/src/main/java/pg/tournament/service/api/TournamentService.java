package pg.tournament.service.api;

import pg.tournament.entity.Tournament;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TournamentService {
    Optional<Tournament> find(UUID id);

    List<Tournament> findAll();

    void update(Tournament tournament);

    void create(Tournament tournament);

    void delete(UUID id);
}
