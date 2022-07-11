package com.assignment.springboot.repository;

import com.assignment.springboot.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Image ,Integer> {
	Image findImageByProductId(int id);
}
