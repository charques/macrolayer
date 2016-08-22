package io.macrolayer.servers.users;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Import;

import io.macrolayer.services.users.UserRepository;
import io.macrolayer.services.users.UsersConfiguration;

/**
 * Run as a micro-service, registering with the Discovery Server (Eureka).
 * <p>
 * Note that the configuration for this application is imported from
 * {@link UsersConfiguration}. This is a deliberate separation of concerns.
 * 
 */
@EnableAutoConfiguration
@EnableDiscoveryClient
@Import(UsersConfiguration.class)
public class UsersServer {

	@Autowired
	protected UserRepository userRepository;

	protected Logger logger = Logger.getLogger(UsersServer.class.getName());

	/**
	 * Run the application using Spring Boot and an embedded servlet engine.
	 * 
	 * @param args
	 *            Program arguments - ignored.
	 */
	public static void main(String[] args) {
		// Tell server to look for users-server.properties or
		// users-server.yml
		System.setProperty("spring.config.name", "users-server");

		SpringApplication.run(UsersServer.class, args);
	}
}
