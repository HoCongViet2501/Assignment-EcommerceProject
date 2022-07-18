package com.assignment.springboot.service.impl;

import com.assignment.springboot.dto.responsedto.ImageDTO;
import com.assignment.springboot.entity.Images;
import com.assignment.springboot.entity.Product;
import com.assignment.springboot.exceptions.ResourceNotFoundException;
import com.assignment.springboot.repository.ImageRepository;
import com.assignment.springboot.repository.ProductRepository;
import com.assignment.springboot.service.ImageService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class ImageServiceImpl implements ImageService {
	private ImageRepository imageRepository;
	private ModelMapper modelMapper;
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	public ImageServiceImpl(ImageRepository imageRepository, ModelMapper modelMapper) {
		this.imageRepository = imageRepository;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public ImageDTO createImage(long productId, MultipartFile multipartFile) throws IOException {
		Images image = new Images();
		Product product = productRepository.findById(productId).orElseThrow(() ->
				new ResourceNotFoundException("not.found.product.have.id " + productId));
		image.setProduct(product);
		image.setFileType(multipartFile.getContentType());
		image.setFile(multipartFile.getBytes());
		this.imageRepository.save(image);
		return modelMapper.map(image, ImageDTO.class);
	}
	
	@Override
	public void updateImage(long id, MultipartFile multipartFile) throws IOException {
		Images image = this.imageRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("not.found.image.have.id " + id));
		image.setFile(multipartFile.getBytes());
		image.setFileType(multipartFile.getContentType());
		this.imageRepository.save(image);
		modelMapper.map(image, ImageDTO.class);
	}
	
	@Override
	public void deleteImage(long id) {
		Images image = this.imageRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("not.found.image.have.id " + id));
		this.imageRepository.delete(image);
	}
	
	@Override
	public ImageDTO getImageByProductId(long id) {
		Images image = this.imageRepository.findImageByProductId(id);
		if (image == null) {
			throw new ResourceNotFoundException("not.found.image.have.productId " + id);
		}
		return modelMapper.map(image, ImageDTO.class);
	}
}
