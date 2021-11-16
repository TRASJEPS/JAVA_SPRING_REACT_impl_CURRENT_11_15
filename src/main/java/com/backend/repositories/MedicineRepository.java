package com.backend.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backend.models.Medicine;
import com.backend.models.Patient;

@Repository
public interface MedicineRepository extends CrudRepository<Medicine, Long>{
	List<Medicine> findAll();
	
	Medicine getMedicineById(long id);
}
