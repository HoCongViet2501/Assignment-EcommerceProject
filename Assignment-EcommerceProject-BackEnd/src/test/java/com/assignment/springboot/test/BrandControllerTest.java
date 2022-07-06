package com.assignment.springboot.test;

import com.assignment.springboot.data.dto.BrandDTO;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class BrandControllerTest extends AbstractTest {
	@Override
	@Before
	public void setUp() {
		super.setUp();
	}
	
	@Test
	public void getListBrandsTest() throws Exception {
		String uri = "/api/brands";
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		Assertions.assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		BrandDTO[] brandDTOS = super.mapFromJson(content, BrandDTO[].class);
		Assert.assertTrue(brandDTOS.length > 0);
	}
	
	@Test
	public void createBrand() throws Exception {
		String uri = "/api/brands";
		BrandDTO brandDto = new BrandDTO();
		brandDto.setName("test");
		brandDto.setDescription("test desc");
		brandDto.setPhoneNumber("test phone number");
		brandDto.setAddress("test address");
		String input = super.mapToJson(brandDto);
		MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(input)).andReturn();
		int status=mvcResult.getResponse().getStatus();
		Assert.assertEquals(201,status);
		Assert.assertEquals(mvcResult.getResponse().getContentAsString(),input);
	}
	@Test
	public void getBrandByNameTest() throws Exception {
		String uri="/api/brands/name?name=Intel";
		MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.ALL)).andReturn();
		int status=mvcResult.getResponse().getStatus();
		Assert.assertEquals(200,status);
		String content=mvcResult.getResponse().getContentAsString();
		BrandDTO brandDTOS = super.mapFromJson(content, BrandDTO.class);
		Assert.assertEquals(content,mapToJson(brandDTOS));
	}
	@Test
	public void updateBrand() throws Exception {
		String uri="/api/brands/7";
		BrandDTO brandDto = new BrandDTO();
		brandDto.setId(7);
		brandDto.setPhoneNumber("21110101");
		String input= super.mapToJson(brandDto);
		MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.put(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(input)).andReturn();
		int status=mvcResult.getResponse().getStatus();
		Assert.assertEquals(200,status);
		Assert.assertEquals(mvcResult.getResponse().getContentAsString(),input);
	}
	@Test
	public void deleteBrandTest() throws Exception {
		String uri="/api/brands/24";
		MvcResult mvcResult=mockMvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status= mvcResult.getResponse().getStatus();
		Assert.assertEquals(200,status);
		Assert.assertEquals(mvcResult.getResponse().getContentAsString(),"Delete.success.brand.have.id 24");
	}
}
