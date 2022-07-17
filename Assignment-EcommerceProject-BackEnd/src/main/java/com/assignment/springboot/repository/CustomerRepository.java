package com.assignment.springboot.repository;

import com.assignment.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<User, Long> {
    User findCustomerByPhoneNumber(String phoneNumber);
}
