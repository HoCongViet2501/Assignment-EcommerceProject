package com.assignment.springboot.service.impl;

import com.assignment.springboot.data.dto.CategoryDTO;
import com.assignment.springboot.data.entity.Category;
import com.assignment.springboot.exception.ResourceNotFoundException;
import com.assignment.springboot.repository.CategoryRepository;
import com.assignment.springboot.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
        log.info("get all category");
        List<Category> categories = this.categoryRepository.findAll();
        if (!categories.isEmpty()) {
            List<CategoryDTO> categoryDTOS = new ArrayList<>();
            for (Category category : categories) {
                categoryDTOS.add(modelMapper.map(category, CategoryDTO.class));
            }
            return categoryDTOS;
        }
        return Collections.emptyList();
    }

    @Override
    public CategoryDTO findCategoryByName(String name) {
        log.info("find category by name");
        Category category = this.categoryRepository.findCategoriesByName(name);
        CategoryDTO categoryDTO = modelMapper.map(category, CategoryDTO.class);
        if (category != null) {
            return categoryDTO;
        } else {
            throw new ResourceNotFoundException("Can't find category have name : " + name);
        }
    }

    @Override
    public boolean deleteCategory(int id) {
        log.info("delete category by id");
        Category category = this.categoryRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("can't find category have id " + id));
        this.categoryRepository.delete(category);
        return true;
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO categoryDTO, int id) {
        log.info("update category");
        Optional<Category> optionalCategory = this.categoryRepository.findById(id);
        if (optionalCategory.isPresent()) {
            Category category = optionalCategory.get();
            modelMapper.map(categoryDTO, category);
            this.categoryRepository.save(category);
            return categoryDTO;
        } else {
            throw new ResourceNotFoundException("can't find category have id: " + id);
        }
    }

    @Override
    public boolean saveCategory(CategoryDTO categoryDTO) {
        log.info("save new category to database");
        Category category = modelMapper.map(categoryDTO, Category.class);
        this.categoryRepository.save(category);
        return true;
    }

}
