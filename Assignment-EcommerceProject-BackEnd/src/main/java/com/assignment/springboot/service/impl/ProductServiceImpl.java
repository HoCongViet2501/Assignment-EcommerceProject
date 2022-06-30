package com.assignment.springboot.service.impl;

import com.assignment.springboot.data.dto.ProductDTO;
import com.assignment.springboot.data.entity.Product;
import com.assignment.springboot.exception.ResourceNotFoundException;
import com.assignment.springboot.repository.ProductRepository;
import com.assignment.springboot.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductDTO> getProducts() {
        List<Product> products = this.productRepository.findAll();
        if (products.isEmpty()) {
            throw new ResourceNotFoundException("Cant.get.list.product");
        }
        return products.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private ProductDTO mapToDto(Product product) {
        return this.modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public ProductDTO findProductById(int id) {
        Product product = this.productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Book.not.found.in.id: " + id));
        return mapToDto(product);
    }

    @Override
    public ProductDTO createProduct(ProductDTO productDTO) {
        productDTO.setCreatedDate(new Date());
        productDTO.setUpdatedDate(new Date());
        this.productRepository.save(modelMapper.map(productDTO, Product.class));
        return productDTO;
    }

    @Override
    public void deleteProduct(int id) {
        Product product = this.productRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Can't.find.product.have.id: " + id));
        this.productRepository.delete(product);
    }

    @Override
    public ProductDTO updateProduct(ProductDTO productDTO, int id) {
        log.info("update.product.have.id " + id);
        Optional<Product> product = this.productRepository.findById(id);
        if (product.isPresent()) {
            throw new ResourceNotFoundException("Can't.find.product.have.id: " + id);
        }
        this.productRepository.save(modelMapper.map(productDTO, Product.class));
        return productDTO;
    }
}
