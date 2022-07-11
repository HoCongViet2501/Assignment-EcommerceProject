package com.assignment.springboot.service;

import com.assignment.springboot.dto.ImageDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
	ImageDTO getImageByID(int id);
	ImageDTO createImage(int productId, MultipartFile multipartFile) throws IOException;
	ImageDTO updateImage(int id, MultipartFile multipartFile) throws IOException;
	void deleteImage(int id);
	ImageDTO getImageByProductId(int id);
}
