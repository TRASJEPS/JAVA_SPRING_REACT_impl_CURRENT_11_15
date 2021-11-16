package com.backend.services;

import java.util.List;

import com.backend.models.Medicine;

//THIS IS AN INTERFACE
// THis is SERVICEs will replace the old service
public interface MedicineServices 
{
	Medicine saveMedicine(Medicine medicine);
	List<Medicine> getAllMedicines();
	Medicine getMedicineById(long id);
	Medicine updateMedicine(Medicine medicine, long id);
	void deleteMedicine(long id);
}