package com.assignment.springboot.service;

import com.assignment.springboot.dto.requestdto.CustomerDtoRequest;
import com.assignment.springboot.dto.responsedto.CustomerDtoResponse;

import java.util.List;

public interface CustomerService {
    List<CustomerDtoResponse> getAllCustomer();

    void deleteCustomer(long id);

    CustomerDtoResponse updateCustomer(CustomerDtoRequest customerDtoRequest, long id);
    
    CustomerDtoResponse createCustomer(CustomerDtoRequest customerDtoRequest);

    CustomerDtoResponse findCustomerByPhoneNumber(String phoneNumber);
}
