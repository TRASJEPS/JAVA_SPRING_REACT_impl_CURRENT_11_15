package com.backend.services;

import java.util.List;

import com.backend.models.Patient;

//THIS IS AN INTERFACE
// THis is SERVICEs will replace the old service
public interface PatientServices {
	Patient savePatient(Patient patient);
	List<Patient> getAllPatients();
	Patient getPatientById(long id);
	Patient updatePatient(Patient patient, long id);
	void deletePatient(long id);
}