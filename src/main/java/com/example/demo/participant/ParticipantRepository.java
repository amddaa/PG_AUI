package com.example.demo.participant;
import com.example.demo.tournament.Tournament;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ParticipantRepository extends JpaRepository<Participant, UUID> {
    Optional<Participant> findByUuid(UUID id);

    List<Participant> findAllBySurname(String surname);

    List<Participant> findAllByTournament(Tournament tournament);
}
