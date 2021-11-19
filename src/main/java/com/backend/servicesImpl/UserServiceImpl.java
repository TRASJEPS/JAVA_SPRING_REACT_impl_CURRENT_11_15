package com.backend.servicesImpl;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.backend.models.User;
import com.backend.repositories.UserRepository;
import com.backend.services.UserServices;

// REMEMBER impliments user SERVICES with an SSSSSS
@Service
public class UserServiceImpl implements UserServices
{
private UserRepository uRepo;
	
	//Constructor
	public UserServiceImpl(UserRepository uRepo) {
		super();
		this.uRepo = uRepo;
	}
	
	@Override
	public User registerUser(User user) {
		String hash = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hash);
		return this.uRepo.save(user);
	}
	
	@Override
	public boolean authenticateUser(String email, String password) {
		User user = this.uRepo.findByEmail(email);
		
		if(user==null) {
			return false;
		}
		return BCrypt.checkpw(password, user.getPassword());
	}


	@Override
	public List<User> getAllUsers() {
		return uRepo.findAll();
	}

	@Override
	public User getUserById(long id) {
		return uRepo.getById(id);
	}

	@Override
	public User updateUser(User user, long id) {
		User existingUser = uRepo.getById(id);
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		existingUser.setPassword(user.getPassword());
		existingUser.setConfirmPassword(user.getConfirmPassword());
		return existingUser;
	}

	@Override
	public void deleteUser(long id) {
		uRepo.getById(id);
		uRepo.deleteById(id);
		
	}

}
