package pg;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.Collections;
import java.util.Arrays;

@SpringBootApplication
@EnableDiscoveryClient
public class LabApplication {

	public static void main(String[] args) {
		SpringApplication.run(LabApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocator(
			RouteLocatorBuilder builder,
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
						.uri("lb://lab-tournament")
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
						.uri("lb://lab-participant")
				)
				.build();
	}

	@Bean
	public CorsWebFilter corsWebFilter() {

		final CorsConfiguration corsConfig = new CorsConfiguration();
		corsConfig.setAllowedOrigins(Collections.singletonList("*"));
		corsConfig.setMaxAge(3600L);
		corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "DELETE", "PUT", "PATCH"));
		corsConfig.addAllowedHeader("*");

		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsConfig);

		return new CorsWebFilter(source);
	}
}
