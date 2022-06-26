package com.assignment.springboot.service;

import com.assignment.springboot.data.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    List<ProductDTO> getProducts();

    ProductDTO findProductById(int id);
    ProductDTO saveProduct(ProductDTO productDTO);
    void deleteProduct(int id);
}
