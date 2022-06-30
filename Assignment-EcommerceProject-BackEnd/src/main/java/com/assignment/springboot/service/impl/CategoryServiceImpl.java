package com.assignment.springboot.service.impl;

import com.assignment.springboot.data.dto.CategoryDTO;
import com.assignment.springboot.data.entity.Category;
import com.assignment.springboot.exception.ResourceNotFoundException;
import com.assignment.springboot.repository.CategoryRepository;
import com.assignment.springboot.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<CategoryDTO> getCategories() {
        List<Category> categories = this.categoryRepository.findAll();
        if (categories.isEmpty()) {
            throw new ResourceNotFoundException("Can't.get.list.categories");
        }
        return categories.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private CategoryDTO mapToDto(Category category) {
        return this.modelMapper.map(category, CategoryDTO.class);
    }

    @Override
    public CategoryDTO findCategoryByName(String name) {
        log.info("find.category.by.name");
        Category category = this.categoryRepository.findCategoriesByName(name);
        if (category == null) {
            throw new ResourceNotFoundException("Can't.find.category.have.name : " + name);
        }
        return mapToDto(category);
    }

    @Override
    public void deleteCategory(int id) {
        log.info("delete category by id");
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("can't.find.category.have.id " + id));
        this.categoryRepository.delete(category);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, int id) {
        Optional<Category> optionalCategory = this.categoryRepository.findById(id);
        if (optionalCategory.isEmpty()) {
            throw new ResourceNotFoundException("can't.find.category.have.id: " + id);
        }
        Category category = optionalCategory.get();
        mapToDto(category);
        this.categoryRepository.save(category);
        return categoryDTO;
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO) {
        this.categoryRepository.save(modelMapper.map(categoryDTO, Category.class));
        return categoryDTO;
    }

}
