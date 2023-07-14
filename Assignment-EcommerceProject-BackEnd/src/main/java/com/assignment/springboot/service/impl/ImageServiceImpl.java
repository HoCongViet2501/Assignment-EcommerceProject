package com.assignment.springboot.service.impl;

import com.assignment.springboot.dto.response.ImageDTO;
import com.assignment.springboot.entity.Image;
import com.assignment.springboot.exceptions.BadRequestException;
import com.assignment.springboot.exceptions.ResourceNotFoundException;
import com.assignment.springboot.repository.ImageRepository;
import com.assignment.springboot.repository.ProductRepository;
import com.assignment.springboot.service.ImageService;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import static java.net.URLEncoder.encode;

@Service
public class ImageServiceImpl implements ImageService {
    private static final String DOWNLOAD_URL = "https://firebasestorage.googleapis.com/v0/b/assignment-nashtech.appspot.com/o/%s?alt=media&token=%s";

    private static final String BUCKET_NAME = "assignment-nashtech.appspot.com";

    private final ImageRepository imageRepository;

    private final ProductRepository productRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository, ProductRepository productRepository) {
        this.imageRepository = imageRepository;
        this.productRepository = productRepository;
    }


    @Override
    public ImageDTO upload(MultipartFile multipartFile, Long productId) {
        try {
            String fileName = UUID.randomUUID().toString().concat(this.getExtension(Objects.requireNonNull(multipartFile.getOriginalFilename())));
            File file = this.convertToFile(multipartFile, fileName);
            String url = this.uploadFile(file, fileName);
            file.delete();

            Image image = new Image();
            image.setFileUrl(url);
            image.setProduct(this.productRepository.findById(productId).orElseThrow(
                    () -> new ResourceNotFoundException("not found product")
            ));
            return ImageDTO.builder()
                    .id(image.getId())
                    .url(image.getFileUrl())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new BadRequestException("error while upload file");
        }
    }

    @Override
    public void deleteImage(long idImage) {
        Image image = this.imageRepository.findById(idImage).orElseThrow(
                () -> new ResourceNotFoundException("not found image"));
        this.imageRepository.delete(image);
        Storage storage = getStorageService();
        BlobId blobId = BlobId.of(BUCKET_NAME, image.getFileUrl());
        storage.delete(blobId);
    }

    @Override
    public ImageDTO getImageByProductId(long id) {
        Image image = this.imageRepository.findImageByProductId(id).orElseThrow(
                () -> new ResourceNotFoundException("not found image have product id: " + id));

        return ImageDTO.builder()
                .id(image.getId())
                .url(image.getFileUrl())
                .build();
    }

    @SneakyThrows
    private String uploadFile(File file, String fileName) {
        BlobId blobId = BlobId.of(BUCKET_NAME, fileName);
        Map<String, String> map = new HashMap<>();
        map.put("storageToken", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setMetadata(map).setContentType("media").build();
        Storage storage = this.getStorageService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        return String.format(DOWNLOAD_URL, encode(fileName, StandardCharsets.UTF_8), encode(fileName, StandardCharsets.UTF_8));
    }

    private Storage getStorageService() {
        try {
            Credentials credential = GoogleCredentials
                    .fromStream(Files.newInputStream(Paths.get("D:\\personal\\code\\Assignment-EcommerceProject\\Assignment-EcommerceProject-BackEnd\\src\\main\\resources\\private\\credentials.json")));
            return StorageOptions.newBuilder().setCredentials(credential).build().getService();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private File convertToFile(MultipartFile multipartFile, String fileName) {
        File file = new File(fileName);
        try (FileOutputStream fileOutputStream = new FileOutputStream(file)) {
            fileOutputStream.write(multipartFile.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }
}
