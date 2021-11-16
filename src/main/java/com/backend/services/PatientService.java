package com.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.models.Medicine;
import com.backend.models.Patient;
import com.backend.repositories.PatientRepository;

@Service
public class PatientService {

	@Autowired
	private PatientRepository pRepo;
	
	//Get All Patients
	public List<Patient> getAllPatients(){
		return this.pRepo.findAll();
	}
	
	//Get One Patient
	public Patient getOnePatient(Long id) {
		return this.pRepo.findById(id).orElse(null);
	}

	//Create Patient
	public Patient createPatient(Patient patient) {
		return this.pRepo.save(patient);
	}
	
	//Update a Patient
		public Patient updatePatient(Patient patient) {
			return this.pRepo.save(patient);
		}
		
	//Delete a Patient
		public void deletePatient(Long id) {
			this.pRepo.deleteById(id);
		}
		
	
	
}
