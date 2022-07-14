package com.assignment.springboot.service;

import com.assignment.springboot.dto.response.ImageDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageService {
	ImageDTO getImageByID(long id);
	ImageDTO createImage(long productId, MultipartFile multipartFile) throws IOException;
	void updateImage(long id, MultipartFile multipartFile) throws IOException;
	void deleteImage(long id);
	ImageDTO getImageByProductId(long id);
}
