package com.assignment.springboot.service;

import com.assignment.springboot.dto.request.CustomerDtoRequest;
import com.assignment.springboot.dto.response.CustomerDtoResponse;

import java.util.List;

public interface CustomerService {
    List<CustomerDtoResponse> getAllCustomer();

    void deleteCustomer(long id);

    CustomerDtoResponse updateCustomer(CustomerDtoRequest customerDtoRequest, long id);
    
    CustomerDtoResponse createCustomer(CustomerDtoRequest customerDtoRequest);

    CustomerDtoResponse findCustomerByPhoneNumber(String phoneNumber);
}
