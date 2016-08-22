package io.macrolayer.services.users;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.macrolayer.services.users.exceptions.UserNotFoundException;

/**
 * A RESTFul controller for accessing user information.
 */
@RestController
public class UsersController {

	protected Logger logger = Logger.getLogger(UsersController.class
			.getName());
	protected UserRepository userRepository;

	/**
	 * Create an instance plugging in the respository of Users.
	 * 
	 * @param userRepository
	 *            An user repository implementation.
	 */
	@Autowired
	public UsersController(UserRepository userRepository) {
		this.userRepository = userRepository;

		logger.info("UserRepository says system has "
				+ userRepository.countUsers() + " users");
	}

	/**
	 * Fetch an user with the specified user number.
	 * 
	 * @param userNumber
	 *            A numeric, 9 digit user number.
	 * @return The user if found.
	 * @throws UserNotFoundException
	 *             If the number is not recognised.
	 */
	@RequestMapping("/usuarios/{userNumber}")
	public User byNumber(@PathVariable("userNumber") String userNumber) {

		logger.info("users-service byNumber() invoked: " + userNumber);
		User user = userRepository.findByNumber(userNumber);
		logger.info("users-service byNumber() found: " + userNumber);

		if (user == null)
			throw new UserNotFoundException(userNumber);
		else {
			return user;
		}
	}
	
	
}
