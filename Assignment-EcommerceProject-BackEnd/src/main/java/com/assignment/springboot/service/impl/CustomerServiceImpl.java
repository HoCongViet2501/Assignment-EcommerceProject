package com.assignment.springboot.service.impl;

import com.assignment.springboot.dto.requestdto.CustomerDtoRequest;
import com.assignment.springboot.dto.responsedto.CustomerDtoResponse;
import com.assignment.springboot.entity.User;
import com.assignment.springboot.exceptions.ResourceNotFoundException;
import com.assignment.springboot.repository.CustomerRepository;
import com.assignment.springboot.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {
	private final CustomerRepository customerRepository;
	private final ModelMapper modelMapper;
	
	@Autowired
	public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
		this.customerRepository = customerRepository;
		this.modelMapper = modelMapper;
	}
	
	@Override
	public List<CustomerDtoResponse> getAllCustomer() {
		List<User> customers = this.customerRepository.findAll();
		if (customers.isEmpty()) {
			throw new ResourceNotFoundException("Can't.get.all.Customer");
		}
		return customers.stream().map(this::mapToDto).collect(Collectors.toList());
	}
	
	private CustomerDtoResponse mapToDto(User customer) {
		return this.modelMapper.map(customer, CustomerDtoResponse.class);
	}
	
	@Override
	public void deleteCustomer(long id) {
		User customer = this.customerRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("can't.find.customer.have.id " + id));
		this.customerRepository.delete(customer);
	}
	
	@Override
	public CustomerDtoResponse updateCustomer(CustomerDtoRequest customerDtoRequest, long id) {
		User customer = this.customerRepository.findById(id).orElseThrow(
				() -> new ResourceNotFoundException("can't.find.customer.have.id: " + id));
		modelMapper.map(customerDtoRequest,customer);
		this.customerRepository.save(customer);
		return mapToDto(customer);
	}
	
	@Override
	public CustomerDtoResponse createCustomer(CustomerDtoRequest customerDtoRequest) {
		User customer=this.modelMapper.map(customerDtoRequest, User.class);
		this.customerRepository.save(customer);
		return mapToDto(customer);
	}
	
	@Override
	public CustomerDtoResponse findCustomerByPhoneNumber(String phoneNumber) {
		log.info("find.customer.by.phone.number");
		User customer = this.customerRepository.findCustomerByPhoneNumber(phoneNumber);
		if (customer == null) {
			throw new ResourceNotFoundException("Can't.find.category.have.name : " + phoneNumber);
		}
		return mapToDto(customer);
	}
}
