package com.assignment.springboot.repository;

import com.assignment.springboot.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	Page<Product> findProductsByNameContainingIgnoreCaseOrderByPriceAsc(String name, Pageable pageable);
	
	Page<Product> findAllByOrderByPriceAsc(Pageable pageable);
	
	Page<Product> findProductsByGenderOrderByPriceAsc(String gender, Pageable pageable);
	
	Page<Product> findProductsByBrandName(String brandName, Pageable pageable);
	
	Page<Product> findProductsByCategoryName(String categoryName, Pageable pageable);
	Page<Product> findProductByPriceBetweenOrderByPriceDesc(float startPrice,float endPrice,Pageable pageable);
}
