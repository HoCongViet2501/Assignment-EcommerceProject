package com.assignment.springboot.service.impl;

import com.assignment.springboot.dto.request.CategoryDtoRequest;
import com.assignment.springboot.dto.response.CategoryDtoResponse;
import com.assignment.springboot.entity.Category;
import com.assignment.springboot.exceptions.ResourceNotFoundException;
import com.assignment.springboot.repository.CategoryRepository;
import com.assignment.springboot.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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
	public List<CategoryDtoResponse> getCategories() {
		List<Category> categories = this.categoryRepository.findAll();
		if (categories.isEmpty()) {
			throw new ResourceNotFoundException("Can't.get.list.categories");
		}
		return categories.stream().map(this::mapToDto).collect(Collectors.toList());
	}
	
	private CategoryDtoResponse mapToDto(Category category) {
		return this.modelMapper.map(category, CategoryDtoResponse.class);
	}
	
	@Override
	public CategoryDtoResponse findCategoryByName(String name) {
		log.info("find.category.by.name");
		Category category = this.categoryRepository.findCategoriesByName(name);
		if (category == null) {
			throw new ResourceNotFoundException("Can't.find.category.have.name : " + name);
		}
		return mapToDto(category);
	}
	
	@Override
	public void deleteCategory(long id) {
		log.info("delete category by id");
		Category category = this.categoryRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("can't.find.category.have.id " + id));
		this.categoryRepository.delete(category);
	}
	
	@Override
	public CategoryDtoResponse updateCategory(CategoryDtoRequest categoryDtoRequest, long id) {
		Category category = this.categoryRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("can't.find.category.have.id: " + id));
		modelMapper.map(categoryDtoRequest,category);
		this.categoryRepository.save(category);
		return mapToDto(category);
	}
	
	@Override
	public CategoryDtoResponse createCategory(CategoryDtoRequest categoryDtoRequest) {
		Category category = this.categoryRepository.save(modelMapper.map(categoryDtoRequest, Category.class));
		return mapToDto(category);
	}
	
}
