package com.example.demo.tournament.service.api;

import com.example.demo.participant.Participant;
import com.example.demo.tournament.Tournament;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TournamentService {
    Optional<Tournament> find(UUID id);

    List<Tournament> findAll();

    void create(Tournament tournament);
}
