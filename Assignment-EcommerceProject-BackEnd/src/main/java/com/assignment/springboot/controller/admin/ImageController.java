package com.assignment.springboot.controller.admin;

import com.assignment.springboot.dto.response.ImageDTO;
import com.assignment.springboot.service.ImageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;

@RestController
@PreAuthorize("hasAuthority('ADMIN')")
@RequestMapping("/api/admin/image")
@Log4j2
public class ImageController {
    @Autowired
    private ImageService imageService;

    @GetMapping("/{id}")
    @Operation(summary = "find image by productId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "find.success"),
            @ApiResponse(responseCode = "404", description = "not.found.image")
    })
    public ResponseEntity<ImageDTO> findImageByProductId(@PathVariable long id) {
        ImageDTO imageDto = imageService.getImageByProductId(id);
        return ResponseEntity.ok().body(imageDto);
    }

    @Operation(summary = "create image by productId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "image uploaded"),
            @ApiResponse(responseCode = "404", description = "not.found.product"),
            @ApiResponse(responseCode = "400", description = "bad.request")
    })
    @PostMapping(value = "/upload", consumes = "multipart/form-data")
    public ResponseEntity<Object> uploadImage(@RequestParam Long idProduct, @RequestParam("file") MultipartFile file) throws Exception {
        log.info("upload image: {}", file.getOriginalFilename());
        return ResponseEntity.ok().body(this.imageService.upload(file, idProduct));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete image by imageId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "deleted.success"),
            @ApiResponse(responseCode = "404", description = "not.found.image")
    })
    public ResponseEntity<String> removeImage(@PathVariable int id) {
        imageService.deleteImage(id);
        return ResponseEntity.ok().body("delete image successfully");
    }


}
