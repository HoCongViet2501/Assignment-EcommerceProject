package com.assignment.springboot.service;

import com.assignment.springboot.dto.CategoryDTO;
import com.assignment.springboot.entity.Category;
import com.assignment.springboot.exception.ResourceNotFoundException;
import com.assignment.springboot.repository.CategoryRepository;
import com.assignment.springboot.service.impl.CategoryServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith({MockitoExtension.class})
@MockitoSettings(strictness = Strictness.LENIENT)
public class TestCategoryService {
	private CategoryServiceImpl categoryService;
	private CategoryRepository categoryRepository;
	private CategoryDTO categoryDTO;
	private ModelMapper modelMapper;
	private Category category;
	
	@BeforeEach
	public void setUp() {
		category = mock(Category.class);
		categoryDTO = mock(CategoryDTO.class);
		categoryRepository = mock(CategoryRepository.class);
		modelMapper = mock(ModelMapper.class);
		categoryService = new CategoryServiceImpl(categoryRepository, modelMapper);
		when(modelMapper.map(category, CategoryDTO.class)).thenReturn(categoryDTO);
		when(modelMapper.map(categoryDTO, Category.class)).thenReturn(category);
	}
	
	@DisplayName("junit test for findCategoryByName method")
	@Test
	public void findByName_shouldReturnCategory_whenNameOfCategoryExist() {
		when(categoryRepository.findCategoriesByName(anyString())).thenReturn(category);
		categoryDTO = modelMapper.map(category, CategoryDTO.class);
		assertThat(categoryDTO).isEqualTo(categoryService.findCategoryByName(anyString()));
		assertThat(categoryDTO.getName()).isEqualTo(category.getName());
	}
	
	@DisplayName("junit test for updateCategory method")
	@Test
	public void updateCategory_shouldReturnUpdatedCategory() {
		when(categoryRepository.findById(anyInt())).thenReturn(Optional.of(category));
		when(categoryRepository.save(category)).thenReturn(category);
		categoryDTO = modelMapper.map(category, CategoryDTO.class);
		assertThat(categoryDTO).isEqualTo(categoryService.updateCategory(categoryDTO, categoryDTO.getId()));
		assertThat(categoryDTO.getId()).isEqualTo(category.getId());
	}
	
	@DisplayName("junit test for notFoundName exception")
	@Test
	public void findByName_shouldReturnThrowException404_whenNotFound() {
		Exception exception = Assertions.assertThrows(ResourceNotFoundException.class,
				() -> categoryService.findCategoryByName(categoryDTO.getName()));
		assertThat(exception.getMessage()).isEqualTo("Can't.find.category.have.name : " + categoryDTO.getName());
	}
}
