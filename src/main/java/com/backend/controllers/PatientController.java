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

import com.backend.models.Patient;
import com.backend.services.PatientServices;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})   
@RequestMapping("/api/patients")						
public class PatientController {
	@Autowired
	private PatientServices pService;
	

	//Create patient REST API
	@PostMapping
	public ResponseEntity<Patient> registerNewPatient(@Valid @RequestBody Patient patient, BindingResult result) {
		if(result.hasErrors()) {
			result.getFieldErrors()
			.forEach(fe -> System.out.println(fe.getField() + fe.getDefaultMessage()));
			return new ResponseEntity(result.getFieldErrors(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<Patient>(pService.savePatient(patient), HttpStatus.CREATED);
	}
	
	//Get all patient REST API
	@GetMapping
	public List<Patient> getAllPatients() {
		return pService.getAllPatients();
	}
	
	//Get one patient by id REST API
	@GetMapping("/{id}")
	public ResponseEntity<Patient> getPatientById(@PathVariable("id") long patientId) {
		return new ResponseEntity<Patient>(pService.getPatientById(patientId), HttpStatus.OK);
	}
	
	//Update patient by id REST API
	@PutMapping("/{id}")
	public ResponseEntity<Patient> updatePatient(@PathVariable("id") long id, @RequestBody Patient patient) {
		return new ResponseEntity<Patient>(pService.updatePatient(patient, id), HttpStatus.OK);
	}
	
	//Delete patient by id REST API
	@DeleteMapping("/{id}")
	public ResponseEntity<String>deletePatient(@PathVariable("id")long id) {
		pService.deletePatient(id);
		return new ResponseEntity<String>("Patient Deleted Successfully! FOREVER!", HttpStatus.OK);
	}
	
//	@GetMapping("/addmedToPat/id of user/ id of med") 
}
