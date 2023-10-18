package pg.participant.repository.api;
import pg.participant.entity.Participant;
import pg.tournament.entity.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, UUID> {
    Optional<Participant> findById(UUID id);

    List<Participant> findAllBySurname(String surname);

    List<Participant> findAllByTournament(Tournament tournament);
}
