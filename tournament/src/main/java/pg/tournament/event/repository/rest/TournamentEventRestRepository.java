package pg.tournament.event.repository.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import pg.tournament.dto.PatchTournamentRequest;
import pg.tournament.dto.PutTournamentRequest;
import pg.tournament.entity.Tournament;
import pg.tournament.event.repository.api.TournamentEventRepository;

import java.util.UUID;

@Repository
public class TournamentEventRestRepository implements TournamentEventRepository {
    private final RestTemplate restTemplate;

    @Autowired
    public TournamentEventRestRepository(RestTemplate template)
    {
        this.restTemplate = template;
    }

    @Override
    public void delete(UUID id) {
        restTemplate.delete("/api/tournaments/{id}", id);
    }

    @Override
    public void create(PutTournamentRequest request) {
        restTemplate.put("/api/tournaments/{id}", request);
    }
}
