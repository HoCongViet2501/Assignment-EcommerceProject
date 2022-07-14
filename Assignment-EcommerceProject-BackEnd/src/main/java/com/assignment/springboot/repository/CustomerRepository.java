package com.assignment.springboot.repository;

import com.assignment.springboot.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Customer findCustomerByPhoneNumber(String phoneNumber);
}
