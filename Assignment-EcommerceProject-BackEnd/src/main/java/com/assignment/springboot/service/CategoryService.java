package com.assignment.springboot.service;

import com.assignment.springboot.dto.requestdto.CategoryDtoRequest;
import com.assignment.springboot.dto.responsedto.CategoryDtoResponse;

import java.util.List;

public interface CategoryService {
    List<CategoryDtoResponse> getCategories();
    
    CategoryDtoResponse findCategoryByName(String name);

    void deleteCategory(long id);

    CategoryDtoResponse updateCategory(CategoryDtoRequest categoryDtoResponse, long id);
    
    CategoryDtoResponse createCategory(CategoryDtoRequest categoryDTOCategoryDtoResponse);
}
