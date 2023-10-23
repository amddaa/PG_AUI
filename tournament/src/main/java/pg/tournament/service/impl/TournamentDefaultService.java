package pg.tournament.service.impl;

import pg.tournament.entity.Tournament;
import pg.tournament.event.repository.api.TournamentEventRepository;
import pg.tournament.repository.api.TournamentRepository;
import pg.tournament.service.api.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TournamentDefaultService implements TournamentService {
    private final TournamentRepository repository;

    private final TournamentEventRepository eventRepository;

    @Autowired
    public TournamentDefaultService(TournamentRepository repository, TournamentEventRepository eventRepository){
        this.repository=repository;
        this.eventRepository = eventRepository;
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
        eventRepository.delete(id);
    }
}
