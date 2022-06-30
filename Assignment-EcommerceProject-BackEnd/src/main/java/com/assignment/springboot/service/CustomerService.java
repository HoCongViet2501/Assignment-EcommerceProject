package com.assignment.springboot.service;

import com.assignment.springboot.data.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAllCustomer();

    void deleteCustomer(int id);

    CustomerDTO updateCustomer(CustomerDTO customerDTO, int id);

    CustomerDTO createCustomer(CustomerDTO customerDTO);

    CustomerDTO findCustomerByPhoneNumber(String phoneNumber);
}
