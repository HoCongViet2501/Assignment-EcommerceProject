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
@Slf4j
@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;
    private ModelMapper modelMapper;
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }
    @Override
    public List<ProductDTO> getProducts() {
        log.trace("Get all products");
        List<Product> products=this.productRepository.findAll();
        if(!products.isEmpty()){
            List<ProductDTO> productDTOS=new ArrayList<>();
            for(Product product: products){
                productDTOS.add(modelMapper.map(product,ProductDTO.class));
            }
            return productDTOS;
        }

        throw new ResourceNotFoundException("Cant get list product");
    }

    @Override
    public ProductDTO findProductById(int id){
        Optional<Product> optionalProduct=this.productRepository.findById(id);
        if(optionalProduct.isPresent()){
            return modelMapper.map(optionalProduct.get(),ProductDTO.class);
        }
        throw new ResourceNotFoundException("Book not found in id: "+id);
    }

    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        log.info("save new product");
        productDTO.setCreatedDate(new Date());
        Product productMapper=modelMapper.map(productDTO,Product.class);
        this.productRepository.save(productMapper);
        return productDTO;
    }

    @Override
    public void deleteProduct(int id) {
        log.info("delete product by id");
        Optional<Product> product=this.productRepository.findById(id);
        if(product.isPresent()){
            this.productRepository.delete(product.get());

        }else{
            throw new ResourceNotFoundException("Can't find product have id: "+ id);
        }
    }
}
