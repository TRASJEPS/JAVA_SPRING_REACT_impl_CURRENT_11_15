package com.backend.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.models.Medicine;
import com.backend.services.MedicineServices;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})   
@RequestMapping("/api/meds")	
public class MedController {
    @Autowired
    private MedicineServices mService;


    //Create Med REST API
    @PostMapping
    public ResponseEntity<Medicine> registerNewMed(@Valid @RequestBody Medicine med) {
        return new ResponseEntity<Medicine>(mService.saveMedicine(med), HttpStatus.CREATED);
    }

    //Get all Meds REST API
    @GetMapping
    public List<Medicine> getAllMeds() {
        return mService.getAllMedicines();
    }

    //Get one Med by id REST API
    @GetMapping("{id}")
    public ResponseEntity<Medicine> getUserById(@PathVariable("id") long medId) {
        return new ResponseEntity<Medicine>(mService.getMedicineById(medId), HttpStatus.OK);
    }

    //Update Med by id REST API
    @PutMapping("id")
    public ResponseEntity<Medicine> updateMed(@PathVariable("id") long id, @RequestBody Medicine med) {
        return new ResponseEntity<Medicine>(mService.updateMedicine(med, id), HttpStatus.OK);
    }

    //Delete Med by id REST API
    @DeleteMapping("id")
    public ResponseEntity<String>deleteMed(@PathVariable("id")long id) {
        mService.deleteMedicine(id);
        return new ResponseEntity<String>("Med Deleted Successfully!", HttpStatus.OK);
    }

}
