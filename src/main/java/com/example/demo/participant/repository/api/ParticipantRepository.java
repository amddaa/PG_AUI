package com.example.demo.participant.repository.api;
import com.example.demo.participant.entity.Participant;
import com.example.demo.tournament.entity.Tournament;
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
