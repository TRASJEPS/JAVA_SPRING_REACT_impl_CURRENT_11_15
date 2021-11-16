package com.backend.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
// changed from CrudRepository to JPA
import org.springframework.stereotype.Repository;

import com.backend.models.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>  
{

	List<Patient> findAll();
//	List<Patient> findByEmail(String email);
	
	// changed from List<Patient>
	Patient getPatientById(long id);
	
}
