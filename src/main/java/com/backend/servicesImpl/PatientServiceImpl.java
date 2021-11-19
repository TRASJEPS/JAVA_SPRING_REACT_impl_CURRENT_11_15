package com.backend.servicesImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.models.Patient;
import com.backend.repositories.PatientRepository;
import com.backend.services.PatientServices;

@Service
public class PatientServiceImpl implements PatientServices {

	private PatientRepository pRepo;
	
	//Constructor
	public PatientServiceImpl(PatientRepository pRepo) {
		super();
		this.pRepo = pRepo;
	}


	@Override
	public Patient savePatient(Patient patient) {
		return pRepo.save(patient);
	}


	@Override
	public List<Patient> getAllPatients() {
		return pRepo.findAll();
	}


	@Override
	public Patient getPatientById(long id) {
		return pRepo.getPatientById(id);
	}


	@Override
	public Patient updatePatient(Patient patient, long id) {
		Patient existingPatient = pRepo.getPatientById(id);
		existingPatient.setFirstName(patient.getFirstName());
		existingPatient.setLastName(patient.getLastName());
		existingPatient.setEmail(patient.getEmail());
		existingPatient.setAge(patient.getAge());
		existingPatient.setMedicalNumber(patient.getMedicalNumber());
		existingPatient.setNotes(patient.getNotes());
//		existingPatient.setPassword(patient.getPassword());
//		existingPatient.setConfirmPassword(patient.getConfirmPassword());
		return existingPatient;
		
	}

	@Override
	public void deletePatient(long id) {
		pRepo.getById(id);
		pRepo.deleteById(id);
		
	}

}
