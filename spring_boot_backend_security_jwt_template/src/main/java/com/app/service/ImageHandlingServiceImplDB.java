package com.app.service;

import java.io.IOException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.app.dao.ProductDao;
import com.app.dto.ApiResponse;
import com.app.entity.Product;


@Service("image_db")
@Transactional
public class ImageHandlingServiceImplDB implements ImageHandlingService {
	@Autowired
	private ProductDao productDao;

	@Override
	public String uploadImage(Long id, MultipartFile image) 
			throws IOException {
		Optional<Product> p = productDao.findById(id);		
		p.get().setImage(image.getBytes());
		return "Image file uploaded successfully";
	}

	

}
