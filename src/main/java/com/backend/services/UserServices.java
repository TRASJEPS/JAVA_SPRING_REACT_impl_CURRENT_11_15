package com.backend.services;

import java.util.List;

import com.backend.models.User;

// THIS IS AN INTERFACE - WILL REPLACE THE UserService
// SIMPLE SMALL
public interface UserServices 
{
	User saveUser(User user);
	List<User> getAllUsers();
	User getUserById(long id);
	User updateUser(User user, long id);
	void deleteUser(long id);
}
