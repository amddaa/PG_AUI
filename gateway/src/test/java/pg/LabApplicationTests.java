package pg;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.netflix.eureka.serviceregistry.EurekaAutoServiceRegistration;

@SpringBootTest
@MockBean(EurekaAutoServiceRegistration.class)
class LabApplicationTests {

	@Test
	void contextLoads() {
	}

}
