package com.backend.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.models.User;
import com.backend.services.UserServices;
import com.backend.validators.UserValidator;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})   
@RequestMapping("/api/users")						
public class UserController 
{
	@Autowired
	private UserServices uService;
	@Autowired
	private UserValidator validator;

	//Create user REST API
	@PostMapping
	public ResponseEntity<User> registerNewUser(@Valid @RequestBody User user, BindingResult result) {
		validator.validate(user, result);
		if(result.hasErrors()) {
			result.getFieldErrors()
			.forEach(fe -> System.out.println(fe.getField() + fe.getDefaultMessage()));
			return new ResponseEntity(result.getFieldErrors(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<User>(uService.registerUser(user), HttpStatus.CREATED);
	}
	
	//Get all users REST API
	@GetMapping
	public List<User> getAllUsers() {
		return uService.getAllUsers();
	}
	
	//Get one user by id REST API
	@GetMapping("{id}")
	public ResponseEntity<User> getUserById(@PathVariable("id") long userId) {
		return new ResponseEntity<User>(uService.getUserById(userId), HttpStatus.OK);
	}
	
	//Update user by id REST API
	@PutMapping("id")
	public ResponseEntity<User> updateUser(@PathVariable("id") long id, @RequestBody User user) {
		return new ResponseEntity<User>(uService.updateUser(user, id), HttpStatus.OK);
	}
	
	//Delete User by id REST API
	@DeleteMapping("id")
	public ResponseEntity<String>deleteUser(@PathVariable("id")long id) {
		uService.deleteUser(id);
		return new ResponseEntity<String>("User Deleted Successfully! FOREVER!", HttpStatus.OK);
	}
}
