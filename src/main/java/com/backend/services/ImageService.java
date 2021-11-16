package com.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.models.Image;
import com.backend.models.Medicine;
import com.backend.repositories.ImageRepository;

@Service
public class ImageService {

	@Autowired
	private ImageRepository iRepo;
	
	//Create Image Object/Save to DB
	public void uploadImage(String url, Medicine medicine) {
		Image newImage = new Image(url, medicine);
		this.iRepo.save(newImage);
	}
	
	public List<Image> medImage(Medicine medicine){
		return this.iRepo.findAllByMedicine(medicine);
	}
	
	public Image createImag(Image image) {
		return this.iRepo.save(image);
	}
	
}
