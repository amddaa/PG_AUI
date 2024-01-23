package pg.tournament.event.repository.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import pg.tournament.dto.PatchTournamentRequest;
import pg.tournament.dto.PutTournamentRequest;
import pg.tournament.event.repository.api.TournamentEventRepository;

import java.util.UUID;

@Repository
public class TournamentEventRestRepository implements TournamentEventRepository {
    private final RestTemplate restTemplate;
    private final DiscoveryClient discoveryClient;

    @Autowired
    public TournamentEventRestRepository(RestTemplate template, DiscoveryClient discoveryClient)
    {
        this.restTemplate = template;
        this.discoveryClient = discoveryClient;
    }

    @Override
    public void delete(UUID id) {
        //restTemplate.delete("/api/tournaments/{id}", id);
        String uri = discoveryClient.getInstances("lab-participant").stream()
                .findFirst()
                .orElseThrow()
                .getUri()
                .toString();

        restTemplate.delete(uri + "/api/tournaments/{id}", id);

    }

    @Override
    public void update(UUID id, PatchTournamentRequest request) {
        String url = "/api/tournaments/" + id;
        restTemplate.put(url, request);
    }

    @Override
    public void create(UUID id, PutTournamentRequest request) {
        String url = "/api/tournaments/" + id;
        restTemplate.put(url, request);
    }
}
