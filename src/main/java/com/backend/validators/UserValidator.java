package com.backend.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.backend.models.User;
import com.backend.repositories.UserRepository;

@Component
public class UserValidator {
	@Autowired
	private UserRepository uRepo;
	
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}
	
	public void validate(Object target, Errors errors) {
		User user = (User)target;
		
		if(!user.getPassword().equals(user.getConfirmPassword())) {
			errors.rejectValue("password", "Match", "Passwords do not match!!! Warning!!!!");
		}
		
		if(this.uRepo.existsByEmail(user.getEmail())) {
			errors.rejectValue("email",  "Match", "Email previously registered!");
		}
	}
}
