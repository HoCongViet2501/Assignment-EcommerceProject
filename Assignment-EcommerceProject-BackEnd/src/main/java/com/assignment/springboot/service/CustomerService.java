package com.assignment.springboot.service;

import com.assignment.springboot.data.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAllCustomer();
    boolean deleteCustomer(int id);
    CustomerDTO updateCustomer(CustomerDTO customerDTO,int id);
    boolean saveCustomer(CustomerDTO customerDTO);
    CustomerDTO findCustomerByPhoneNumber(String phoneNumber);
}
