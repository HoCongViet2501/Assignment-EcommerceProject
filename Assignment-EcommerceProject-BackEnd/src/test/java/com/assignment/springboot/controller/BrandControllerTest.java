package com.assignment.springboot.controller;

import com.assignment.springboot.controller.admin.BrandController;
import com.assignment.springboot.dto.requestdto.BrandDtoRequest;
import com.assignment.springboot.dto.responsedto.BrandDtoResponse;
import com.assignment.springboot.service.BrandService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class BrandControllerTest {
	private final BrandController brandController;
	private final BrandService brandService;
	private MockMvc mockMvc;
	private ObjectMapper objectMapper;
	private List<BrandDtoResponse> brandDtos;
	private BrandDtoRequest brandDtoRequest;
	private BrandDtoResponse brandDtoResponse;
	
	public BrandControllerTest() {
		brandService = mock(BrandService.class);
		brandController = new BrandController(brandService);
	}
	
	@BeforeEach
	void setUp() {
		objectMapper = new ObjectMapper();
		this.mockMvc = MockMvcBuilders.standaloneSetup(brandController).build();
		this.brandDtos = new ArrayList<>();
		this.brandDtos.add(new BrandDtoResponse(0, "test_name", "test desc", "test phone", "test address"));
		this.brandDtos.add(new BrandDtoResponse(1, "test name1", "test desc1", "test phone1", "test address1"));
		brandDtoRequest = new BrandDtoRequest("test name2", "test desc2", "test phone2", "test address2");
		brandDtoResponse= new BrandDtoResponse(2,"test name2", "test desc2", "test phone2", "test address2");
	}
	
	@Test
	public void getBrand_ShouldReturnListBrands_WhenDataAvailable() throws Exception {
		when(brandService.getBrands()).thenReturn(brandDtos);
		this.mockMvc.perform(get("/api/brands")
						.accept(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().is(200))
				.andExpect(MockMvcResultMatchers.jsonPath("$.size()", is(brandDtos.size())))
				.andExpect(jsonPath("$[0].name", is(brandDtos.get(0).getName())))
				.andExpect(content().string(objectMapper.writeValueAsString(brandDtos.toArray())));
	}
	
	@Test
	public void createBrand_thenAddBrandCalled_andBrandReturned() throws Exception {
		when(brandService.createBrand(brandDtoRequest)).thenReturn(brandDtoResponse);
		String contentBrand = objectMapper.writeValueAsString(brandDtoRequest);
		this.mockMvc.perform(post("/api/brands/")
						.contentType(MediaType.APPLICATION_JSON)
						.content(contentBrand))
				.andExpect(status().is(201))
				.andExpect(content().string(objectMapper.writeValueAsString(brandDtoResponse)));
	}
	
	@Test
	public void findBrandByName_shouldReturnBrand_whenNameIsExist() throws Exception {
		String name="test_name";
		when(brandService.findBrandByName(name)).thenReturn(brandDtoResponse);
		this.mockMvc.perform(get("/api/brands/name?name={name}",name)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().is(200))
				.andExpect(jsonPath("$.name", is(brandDtoResponse.getName())))
				.andExpect(content().string(objectMapper.writeValueAsString(brandDtoResponse)));
	}
	
	@Test
	public void updateBrand_shouldReturnBrand_whenUpdateSuccess() throws Exception {
		long id=1L;
		String contentBrand = objectMapper.writeValueAsString(brandDtoRequest);
		when(brandService.updateBrand(brandDtoRequest, id)).thenReturn(brandDtoResponse);
		this.mockMvc.perform(put("/api/brands/{id}",id)
						.contentType(MediaType.APPLICATION_JSON)
						.content(contentBrand))
				.andExpect(status().is(200))
				.andExpect(content().string(objectMapper.writeValueAsString(brandDtoResponse)));
	}
	
	@Test
	public void deleteBrand_shouldReturnNotification_afterDeleteSuccess() throws Exception {
		long id = 1L;
		doNothing().when(brandService).deleteBrand(id);
		this.mockMvc.perform(delete("/api/brands/{id}", id))
				.andExpect(status().is(200))
				.andExpect(content().string("Delete.success.brand.have.id " + id));
	}
	
	@Test
	public void findNonExistingBrandByName_shouldReturnBadRequestException_whenFindByName() throws Exception {
		when(this.brandService.findBrandByName("test.name")).thenReturn(brandDtoResponse);
		this.mockMvc.perform(delete("/api/brands/name?name=","test_name"))
				.andExpect(status().is(HttpStatus.BAD_REQUEST.value()));
	}
}
