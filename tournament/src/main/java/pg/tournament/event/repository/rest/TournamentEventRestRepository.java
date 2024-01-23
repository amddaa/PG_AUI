package pg.tournament.event.repository.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import pg.tournament.dto.PatchTournamentRequest;
import pg.tournament.dto.PutTournamentRequest;
import pg.tournament.event.repository.api.TournamentEventRepository;

import java.util.UUID;

@Repository
public class TournamentEventRestRepository implements TournamentEventRepository {
    private final RestTemplate restTemplate;
    private final LoadBalancerClient loadBalancerClient;

    @Autowired
    public TournamentEventRestRepository(RestTemplate template, LoadBalancerClient loadBalancerClient)
    {
        this.restTemplate = template;
        this.loadBalancerClient = loadBalancerClient;
    }

    @Override
    public void delete(UUID id) {
        //restTemplate.delete("/api/tournaments/{id}", id);
        String uri = loadBalancerClient.choose("lab-participant").getUri().toString();
        restTemplate.delete(uri + "/api/tournaments/{id}", id);
    }

    @Override
    public void update(UUID id, PatchTournamentRequest request) {
        //String url = "/api/tournaments/" + id;
		String uri = loadBalancerClient.choose("lab-participant").getUri().toString();
        restTemplate.put(uri + "/api/tournaments/{id}", request);
    }

    @Override
    public void create(UUID id, PutTournamentRequest request) {
        //String url = "/api/tournaments/" + id;
		String uri = loadBalancerClient.choose("lab-participant").getUri().toString();
        restTemplate.put(uri + "/api/tournaments/{id}", request);
    }
}
