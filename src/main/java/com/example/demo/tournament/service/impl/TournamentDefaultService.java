package com.example.demo.tournament.service.impl;

import com.example.demo.tournament.entity.Tournament;
import com.example.demo.tournament.repository.api.TournamentRepository;
import com.example.demo.tournament.service.api.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TournamentDefaultService implements TournamentService {
    private final TournamentRepository repository;

    @Autowired
    public TournamentDefaultService(TournamentRepository repository){
        this.repository=repository;
    }

    @Override
    public Optional<Tournament> find(UUID id) {
        return repository.findById(id);
    }

    @Override
    public List<Tournament> findAll() {
        return repository.findAll();
    }

    @Override
    public void update(Tournament tournament) {
        repository.save(tournament);
    }

    @Override
    public void create(Tournament tournament) {
        repository.save(tournament);
    }

    @Override
    public void delete(UUID id) {
        repository.findById(id).ifPresent(repository::delete);
    }
}
