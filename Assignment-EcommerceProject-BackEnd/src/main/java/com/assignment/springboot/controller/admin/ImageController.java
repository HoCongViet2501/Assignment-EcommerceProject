package com.assignment.springboot.controller.admin;

import com.assignment.springboot.dto.response.ImageDTO;
import com.assignment.springboot.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

@RestController
@RequestMapping("/api/admin/image")
public class ImageController {
	@Autowired
	private ImageService imageService;
	
	@GetMapping("/{id}")
	@Operation(summary = "find image by imageId")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "find.success"),
			@ApiResponse(responseCode = "404",description = "not.found.image")
	})
	public ResponseEntity<ImageDTO> findImageById(@PathVariable int id) {
		ImageDTO imageDto = imageService.getImageByID(id);
		return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION).body(imageDto);
	}
	@Operation(summary = "create image by productId")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "created.success"),
			@ApiResponse(responseCode = "404",description = "not.found.product")
	})
	@PostMapping(value = "/{idProduct}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<?> createNewImage(@PathVariable int idProduct, @RequestParam("file") MultipartFile multipartFile) {
		ImageDTO imageDTO = null;
		try {
			imageDTO = imageService.createImage(idProduct, multipartFile);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/admin/image")
				.path(imageDTO.getId() + "").toUriString();
		imageDTO.setUrl(url);
		return ResponseEntity.ok().body(imageDTO);
	}
	@Operation(summary = "update image ")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "updated.success"),
			@ApiResponse(responseCode = "404",description = "not.found.image")
	})
	@PutMapping(value = "/{id}",consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
	public ResponseEntity<String> updateNewImage(@PathVariable int id, @RequestParam("file") MultipartFile multipartFile) throws IOException {
		imageService.updateImage(id,multipartFile);
		return ResponseEntity.ok().body("updated.success.image.have.id "+id);
	}
	
	@DeleteMapping("/{id}")
	@Operation(summary = "delete image by imageId")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200",description = "deleted.success"),
			@ApiResponse(responseCode = "404",description = "not.found.image")
	})
	public ResponseEntity<String> removeImage(@PathVariable int id) {
		imageService.deleteImage(id);
		return ResponseEntity.ok().body("deleted.success");
	}
	
	
}
