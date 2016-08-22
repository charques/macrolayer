package io.macrolayer.services.users;

import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import io.macrolayer.servers.users.UsersServer;
import io.macrolayer.services.users.UsersConfiguration;

/**
 * Imitates the {@link UsersServer}, but without using any of the discovery
 * client code. Allows the test to use the same configuration as the
 * <code>UsersServer</code> would.
 */
@SpringBootApplication
@Import(UsersConfiguration.class)
class UsersMain {
	public static void main(String[] args) {
		// Tell server to look for users-server.properties or
		// users-server.yml
		System.setProperty("spring.config.name", "users-server");
		SpringApplication.run(UsersMain.class, args);
	}
}

/**
 * Spring Integration/System test - by using @SpringApplicationConfiguration
 * instead of @ContextConfiguration, it picks up the same configuration that
 * Spring Boot would use.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = UsersMain.class)
public class UsersControllerIntegrationTests extends AbstractUserControllerTests {

}
