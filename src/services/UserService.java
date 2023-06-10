package services;

import models.User;

public interface UserService {
	boolean credentialsAreValid(String username, String password);
}
