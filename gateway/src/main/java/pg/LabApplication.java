package pg;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LabApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocator(
			RouteLocatorBuilder builder,
			@Value("${lab.participant.url}") String participantUrl,
			@Value("${lab.tournament.url}") String tournamentUrl,
			@Value("${lab.gateway.host}") String host
	){
		return builder
				.routes()
				.route("tournaments", route -> route
						.host(host)
						.and()
						.path(
								"/api/tournaments/{uuid}",
								"/api/tournaments"
						)
						.uri(tournamentUrl)
				)
				.route("participants", route -> route
						.host(host)
						.and()
						.path(
								"/api/participants",
								"/api/participants/**",
								"/api/tournaments/{uuid}/participants",
								"/api/tournaments/{uuid}/participants/**"
						)
						.uri(participantUrl)
				)
				.build();
	}

}
