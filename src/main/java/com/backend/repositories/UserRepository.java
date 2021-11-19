package com.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.models.User;
//import com.backend.repositories.List;
//import com.backend.repositories.String;

public interface UserRepository extends JpaRepository<User, Long>
{
	// PASTED FROM CRUD.pharmatrack
	List<User> findAll();
	boolean existsByEmail(String email);
	User findByEmail(String email);
}