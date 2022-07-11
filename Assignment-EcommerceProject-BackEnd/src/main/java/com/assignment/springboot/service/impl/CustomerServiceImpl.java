package com.assignment.springboot.service.impl;

import com.assignment.springboot.dto.CustomerDTO;
import com.assignment.springboot.entity.Customer;
import com.assignment.springboot.exception.ResourceNotFoundException;
import com.assignment.springboot.repository.CustomerRepository;
import com.assignment.springboot.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
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
    public List<CustomerDTO> getAllCustomer() {
        List<Customer> customers = this.customerRepository.findAll();
        if (customers.isEmpty()) {
            throw new ResourceNotFoundException("Can't.get.all.Customer");
        }
        return customers.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private CustomerDTO mapToDto(Customer customer) {
        return this.modelMapper.map(customer, CustomerDTO.class);
    }

    @Override
    public void deleteCustomer(int id) {
        Customer customer = this.customerRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("can't.find.customer.have.id " + id));
        this.customerRepository.delete(customer);
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO, int id) {
        Optional<Customer> customerOptional = this.customerRepository.findById(id);
        if (customerOptional.isEmpty()) {
            throw new ResourceNotFoundException("can't.find.customer.have.id: " + id);
        }
        Customer customer = customerOptional.get();
        mapToDto(customer);
        this.customerRepository.save(customer);
        return customerDTO;
    }

    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        this.customerRepository.save(this.modelMapper.map(customerDTO, Customer.class));
        return customerDTO;
    }

    @Override
    public CustomerDTO findCustomerByPhoneNumber(String phoneNumber) {
        log.info("find.customer.by.phone.number");
        Customer customer = this.customerRepository.findCustomerByPhoneNumber(phoneNumber);
        if (customer == null) {
            throw new ResourceNotFoundException("Can't.find.category.have.name : " + phoneNumber);
        }
        return mapToDto(customer);
    }
}
