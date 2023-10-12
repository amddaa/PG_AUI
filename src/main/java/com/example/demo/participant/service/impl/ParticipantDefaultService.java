package com.example.demo.participant.service.impl;

import com.example.demo.participant.Participant;
import com.example.demo.participant.repository.api.ParticipantRepository;
import com.example.demo.participant.service.api.ParticipantService;
import com.example.demo.tournament.repository.api.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ParticipantDefaultService implements ParticipantService {
    private final ParticipantRepository repository;
    private final TournamentRepository tournamentRepository;

    @Autowired
    public ParticipantDefaultService(
            ParticipantRepository repository,
            TournamentRepository tournamentRepository
    ){
        this.repository = repository;
        this.tournamentRepository = tournamentRepository;
    }

    @Override
    public Optional<Participant> find(UUID id) {
        return repository.findById(id);
    }

    @Override
    public List<Participant> findAll() {
        return repository.findAll();
    }

    @Override
    public void create(Participant participant) {
        repository.save(participant);
    }

    @Override
    public void update(Participant participant) {
        repository.save(participant);
    }

    @Override
    public void delete(UUID id) {
        repository.findById(id).ifPresent(repository::delete);
    }

    @Override
    public Optional<List<Participant>> findAllByTournament(UUID tournamentId) {
        return tournamentRepository.findById(tournamentId)
                .map(repository::findAllByTournament);
    }
}
