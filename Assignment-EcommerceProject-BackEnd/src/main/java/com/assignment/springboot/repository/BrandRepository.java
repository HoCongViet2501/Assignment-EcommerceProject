package com.assignment.springboot.repository;

import com.assignment.springboot.data.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface BrandRepository extends JpaRepository<Brand,Integer> {

}
