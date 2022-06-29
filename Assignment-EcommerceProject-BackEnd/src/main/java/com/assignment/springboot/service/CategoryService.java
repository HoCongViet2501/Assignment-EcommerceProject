package com.assignment.springboot.service;

import com.assignment.springboot.data.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getCategories();
    CategoryDTO findCategoryByName(String name);
    boolean deleteCategory(int id);
    CategoryDTO updateCategory(CategoryDTO categoryDTO, int id);
    boolean saveCategory(CategoryDTO categoryDTO);
}