package com.backend.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.backend.models.Image;
import com.backend.models.Medicine;

@Repository
public interface ImageRepository extends CrudRepository<Image, Long> {

	List<Image> findAllByMedicine(Medicine medicine);
}
