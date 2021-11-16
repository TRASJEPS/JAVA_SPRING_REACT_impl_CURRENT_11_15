package com.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.models.Medicine;
import com.backend.models.Patient;
import com.backend.repositories.MedicineRepository;

@Service
public class MedicineService {
	@Autowired
	private MedicineRepository mRepo;
	
	//Get All
	public List<Medicine> getAllMeds(){
		return this.mRepo.findAll();
	}
	
	//Get One
	public Medicine getOneMed(Long id) {
		return this.mRepo.findById(id).orElse(null);
	}
	
	//Create Med
	public Medicine createMed(Medicine medicine) {
		return this.mRepo.save(medicine);
	}
	
	//Update
	public Medicine updateMed(Medicine medicine) {
		return this.mRepo.save(medicine);
	}
	
	//Delete
	public void destroyMed(Long id) {
		this.mRepo.deleteById(id);
	}
	//Adding Meds to Patients
	public void addMedToPatient(Patient patient, Medicine medicine) {
		List<Patient> patientMeds = medicine.getPatientsOn();
		patientMeds.add(patient);
		this.mRepo.save(medicine);
		}
	
	public void removeMedFromPatient(Patient patient, Medicine medicine) {
		List<Patient> patientMeds = medicine.getPatientsOn();
		patientMeds.add(patient);
		this.mRepo.save(medicine);
		}
	
}
