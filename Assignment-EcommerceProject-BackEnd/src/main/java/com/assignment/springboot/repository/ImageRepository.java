package com.assignment.springboot.repository;

import com.assignment.springboot.entity.Images;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImageRepository extends JpaRepository<Images,Long> {
	Images findImageByProductId(long id);
}
