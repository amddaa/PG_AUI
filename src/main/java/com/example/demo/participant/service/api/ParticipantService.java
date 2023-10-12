package com.example.demo.participant.service.api;

import com.example.demo.participant.Participant;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ParticipantService {
    Optional<Participant> find(UUID id);

    List<Participant> findAll();

    void create(Participant participant);

    void update(Participant participant);

    void delete(UUID id);

    Optional<List<Participant>> findAllByTournament(UUID tournamentId);
}
