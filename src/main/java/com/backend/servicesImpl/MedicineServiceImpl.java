package com.backend.servicesImpl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.models.Medicine;
import com.backend.repositories.MedicineRepository;
import com.backend.services.MedicineServices;

@Service
public class MedicineServiceImpl implements MedicineServices {

	private MedicineRepository mRepo;
	
	//Constructor
	public MedicineServiceImpl(MedicineRepository mRepo) {
		super();
		this.mRepo = mRepo;
	}


	@Override
	public Medicine saveMedicine(Medicine medicine) {
		return mRepo.save(medicine);
	}


	@Override
	public List<Medicine> getAllMedicines() {
		return mRepo.findAll();
	}


	@Override
	public Medicine getMedicineById(long id) {
		return mRepo.getMedicineById(id);
	}

	@Override
	public Medicine updateMedicine(Medicine medicine, long id) {
		Medicine existingMedicine = mRepo.getMedicineById(id);
		existingMedicine.setMedName(medicine.getMedName());
		existingMedicine.setUnitOfMeasure(medicine.getUnitOfMeasure());
		existingMedicine.setPrice(medicine.getPrice());
		existingMedicine.setDosage(medicine.getDosage());
		
		//CHECK if this should be getPerscription because its a T/F
		existingMedicine.setPrescription(medicine.isPrescription());
//		existingPatient.setPassword(patient.getPassword());
//		existingPatient.setConfirmPassword(patient.getConfirmPassword());
		return existingMedicine;
		
	}

	@Override
	public void deleteMedicine(long id) {
		mRepo.getMedicineById(id);
		mRepo.deleteById(id);
		
	}

}
