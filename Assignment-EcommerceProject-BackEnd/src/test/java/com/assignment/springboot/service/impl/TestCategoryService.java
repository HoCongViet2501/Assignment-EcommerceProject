package com.assignment.springboot.service.impl;

import com.assignment.springboot.dto.requestdto.CategoryDtoRequest;
import com.assignment.springboot.dto.responsedto.CategoryDtoResponse;
import com.assignment.springboot.entity.Category;
import com.assignment.springboot.exceptions.ResourceNotFoundException;
import com.assignment.springboot.repository.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestCategoryService {
	private CategoryServiceImpl categoryService;
	private CategoryRepository categoryRepository;
	private CategoryDtoResponse categoryDtoResponse;
	private CategoryDtoRequest categoryDtoRequest;
	private ModelMapper modelMapper;
	private Category category;
	
	@BeforeEach
	public void setUp() {
		category = mock(Category.class);
		categoryDtoResponse = mock(CategoryDtoResponse.class);
		categoryRepository = mock(CategoryRepository.class);
		categoryDtoRequest = mock(CategoryDtoRequest.class);
		modelMapper = mock(ModelMapper.class);
		categoryService = new CategoryServiceImpl(categoryRepository, modelMapper);
		when(modelMapper.map(category, CategoryDtoResponse.class)).thenReturn(categoryDtoResponse);
		when(modelMapper.map(categoryDtoResponse, Category.class)).thenReturn(category);
	}
	
	@DisplayName("junit test for findCategoryByName method")
	@Test
	public void findByName_shouldReturnCategory_whenNameOfCategoryExist() {
		when(categoryRepository.findCategoriesByName("name")).thenReturn(category);
		when(modelMapper.map(category, CategoryDtoResponse.class)).thenReturn(categoryDtoResponse);
		assertThat(categoryService.findCategoryByName("name")).isEqualTo(categoryDtoResponse);
	}
	
	@DisplayName("junit test for updateCategory method")
	@Test
	public void updateCategory_shouldReturnUpdatedCategory() {
		when(categoryRepository.findById(1L)).thenReturn(Optional.of(category));
		when(modelMapper.map(categoryDtoRequest, Category.class)).thenReturn(category);
		when(categoryRepository.save(category)).thenReturn(category);
		when(modelMapper.map(category,CategoryDtoResponse.class)).thenReturn(categoryDtoResponse);
		assertThat(categoryService.updateCategory(categoryDtoRequest, 1L)).isEqualTo(categoryDtoResponse);
	}
	
	@DisplayName("junit test for notFoundName exception")
	@Test
	public void findByName_shouldReturnResourceNotFoundException_whenNotFoundName() {
		Exception exception = Assertions.assertThrows(ResourceNotFoundException.class,
				() -> categoryService.findCategoryByName("name"));
		assertThat(exception.getMessage()).isEqualTo("Can't.find.category.have.name : name");
	}
}
