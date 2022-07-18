package com.assignment.springboot.repository;

import com.assignment.springboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByGmail(String gmail);
	User getUserById(long id);
}
