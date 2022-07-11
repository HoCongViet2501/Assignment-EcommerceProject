package com.assignment.springboot.service;

import com.assignment.springboot.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getProducts();

    ProductDTO findProductById(int id);

    ProductDTO createProduct(ProductDTO productDTO);

    void deleteProduct(int id);

    ProductDTO updateProduct(ProductDTO productDTO, int id);
}
