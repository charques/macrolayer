package io.macrolayer.services.users;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import io.macrolayer.services.users.User;
import io.macrolayer.services.users.UserRepository;
import io.macrolayer.services.users.UsersController;

public class UsersControllerTests extends AbstractUserControllerTests {

	protected static final User theUser = new User(USER_1, USER_1_NAME, SURNAME_1_NAME, SURNAME_2_NAME);

	protected static class TestUserRepository implements UserRepository {

		@Override
		public User findByNumber(String userNumber) {
			if (userNumber.equals(USER_1))
				return theUser;
			else
				return null;
		}

		@Override
		public List<User> findByNameContainingIgnoreCase(String partialName) {
			List<User> users = new ArrayList<User>();

			if (USER_1_NAME.toLowerCase().indexOf(partialName.toLowerCase()) != -1)
				users.add(theUser);

			return users;
		}

		@Override
		public int countUsers() {
			return 1;
		}
	}

	protected TestUserRepository testRepo = new TestUserRepository();

	@Before
	public void setup() {
		userController = new UsersController(testRepo);
	}
}
