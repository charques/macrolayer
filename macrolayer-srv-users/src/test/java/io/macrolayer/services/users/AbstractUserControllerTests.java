package io.macrolayer.services.users;

import java.util.logging.Logger;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import io.macrolayer.services.users.exceptions.UserNotFoundException;

public abstract class AbstractUserControllerTests {

	protected static final String USER_1 = "123456789";
	protected static final String USER_1_NAME = "Keri";
	protected static final String SURNAME_1_NAME = "Lee";
	protected static final String SURNAME_2_NAME = "Martin";

	@Autowired
	UsersController userController;

	@Test
	public void validUserNumber() {
		Logger.getGlobal().info("Start validUserNumber test");
		User user = userController.byNumber(USER_1);

		Assert.assertNotNull(user);
		Assert.assertEquals(USER_1, user.getNumber());
		Assert.assertEquals(USER_1_NAME, user.getName());
		Logger.getGlobal().info("End validUser test");
	}
	
	@Test
	public void invalidUserNumber() {
		try {
			userController.byNumber("10101010");
			Assert.fail("Expected an UserNotFoundException");
		} catch (UserNotFoundException e) {
			// Worked!
		}
	}
}
