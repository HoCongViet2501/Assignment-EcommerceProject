package com.assignment.springboot.service;

import com.assignment.springboot.dto.response.ImageDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
	ImageDTO upload(MultipartFile multipartFile, Long productId) throws Exception;

	void deleteImage(long idImage);

	ImageDTO getImageByProductId(long id);
}
