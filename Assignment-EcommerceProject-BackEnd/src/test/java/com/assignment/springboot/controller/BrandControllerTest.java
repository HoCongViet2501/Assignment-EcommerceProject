package com.assignment.springboot.controller;

import com.assignment.springboot.data.dto.BrandDTO;
import com.assignment.springboot.service.BrandService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BrandControllerTest {
	private final BrandController brandController;
	private final BrandService brandService;
	private MockMvc mockMvc;
	private ObjectMapper objectMapper;
	private List<BrandDTO> brandDtos;
	private BrandDTO brandDto;
	
	public BrandControllerTest() {
		brandService = Mockito.mock(BrandService.class);
		brandController = new BrandController(brandService);
	}
	
	@BeforeEach
	void setUp() {
		objectMapper = new ObjectMapper();
		this.mockMvc = MockMvcBuilders.standaloneSetup(brandController).build();
		this.brandDtos = new ArrayList<>();
		this.brandDtos.add(new BrandDTO(0, "test_name", "test desc", "test phone", "test address"));
		this.brandDtos.add(new BrandDTO(1, "test name1", "test desc1", "test phone1", "test address1"));
		brandDto = new BrandDTO(2, "test name2", "test desc2", "test phone2", "test address2");
	}
	
	@Test
	public void getBrand_ShouldReturnListBrands_WhenDataAvailable() throws Exception {
		BDDMockito.given(brandService.getBrands()).willReturn(brandDtos);
		this.mockMvc.perform(get("/api/brands")
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().is(200))
				.andExpect(MockMvcResultMatchers.jsonPath("$.size()", is(brandDtos.size())))
				.andExpect(jsonPath("$[0].name", is(brandDtos.get(0).getName())))
				.andExpect(content().string(objectMapper.writeValueAsString(brandDtos.toArray())));
	}
	
	@Test
	public void createBrand_thenAddBrandCalled_andBrandReturned() throws Exception {
		when(brandService.createBrand(brandDto)).thenReturn(brandDto);
		String contentBrand = objectMapper.writeValueAsString(brandDto);
		this.mockMvc.perform(post("/api/brands")
						.contentType(MediaType.APPLICATION_JSON)
						.content(contentBrand))
				.andExpect(status().is(HttpStatus.CREATED.value()))
				.andExpect(content().string(contentBrand));
	}
	
	@Test
	public void whenFindBrandByName_shouldReturnBrand() throws Exception {
		when(brandService.findBrandByName("test_name")).thenReturn(brandDto);
		this.mockMvc.perform(get("/api/brands/name?name=test_name")
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(200))
				.andExpect(jsonPath("$.name", is(brandDto.getName())))
				.andExpect(content().string(objectMapper.writeValueAsString(brandDto)));
	}
	
	@Test
	public void updateBrand_shouldReturnBrand_whenUpdated() throws Exception {
		brandDto.setAddress("update address");
		String contentBrand = objectMapper.writeValueAsString(brandDto);
		when(brandService.updateBrand(brandDto, 1)).thenReturn(brandDto);
		this.mockMvc.perform(put("/api/brands/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content(contentBrand))
				.andExpect(status().is(200))
				.andExpect(content().string(contentBrand));
	}
	
	@Test
	public void deleteBrand_shouldReturnNotification_afterDeleteSuccess() throws Exception {
		doReturn(String.class).when(brandService).deleteBrand(brandDto.getId());
		this.mockMvc.perform(delete("/api/brands/{id}", brandDto.getId()))
				.andExpect(status().is(200))
				.andExpect(content().string("Delete.success.brand.have.id " + brandDto.getId()));
	}
	
	@Test
	public void whenDeletingNonExistingBrand_shouldReturn404_whenFindById() throws Exception {
		int id = 40;
		doReturn(String.class).when(brandService).deleteBrand(brandDtos.get(0).getId());
		this.mockMvc.perform(delete("/api/brands/{id}", id))
				.andExpect(status().is(404));
	}
}
