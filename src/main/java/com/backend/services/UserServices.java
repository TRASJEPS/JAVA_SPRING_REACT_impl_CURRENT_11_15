package com.backend.services;

import java.util.List;

import com.backend.models.User;

// THIS IS AN INTERFACE - WILL REPLACE THE UserService
// SIMPLE SMALL
public interface UserServices 
{
	User registerUser(User user);
	boolean authenticateUser(String email, String password);
	List<User> getAllUsers();
	User getUserById(long id);
	User updateUser(User user, long id);
	void deleteUser(long id);
}
