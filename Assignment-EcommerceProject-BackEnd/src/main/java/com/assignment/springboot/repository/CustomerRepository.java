package com.assignment.springboot.repository;

import com.assignment.springboot.data.dto.CustomerDTO;
import com.assignment.springboot.data.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    Customer findCustomerByPhoneNumber(String phoneNumber);
}
