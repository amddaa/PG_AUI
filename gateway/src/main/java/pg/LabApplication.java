package pg;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
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
						.uri("ds://lab-tournament")
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
						.uri("ds://lab-participant")
				)
				.build();
	}

	@Bean
	public GlobalFilter discoveryFilter(DiscoveryClient discoveryClient) {
		return new GlobalFilter() {
			@Override
			@SneakyThrows
			public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
				URI uri = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR);
				if (uri != null && "ds".equals(uri.getScheme())) {
					System.out.println(uri.getHost());
					ServiceInstance instance = discoveryClient.getInstances(uri.getHost()).stream()
							.findFirst()
							.orElseThrow();
					System.out.println(instance.getHost());
					URI newUri = new URI(
							instance.getScheme(),   // Updated scheme
							uri.getUserInfo(),      // Keep the original user info
							instance.getHost(),     // Updated host
							instance.getPort(),     // Updated port
							uri.getPath(),          // Keep the original path
							uri.getQuery(),         // Keep the original query
							uri.getFragment()       // Keep the original fragment
					);
					exchange.getAttributes().put(ServerWebExchangeUtils.GATEWAY_REQUEST_URL_ATTR, newUri);
				}
				return chain.filter(exchange);
			}
		};
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
