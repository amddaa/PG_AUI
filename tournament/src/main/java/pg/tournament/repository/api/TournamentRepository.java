package pg.tournament.repository.api;

import pg.tournament.entity.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import java.util.UUID;

public interface TournamentRepository extends JpaRepository<Tournament, UUID> {
    Optional<Tournament> findById(UUID id);
}
