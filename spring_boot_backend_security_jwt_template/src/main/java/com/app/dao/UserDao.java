package com.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.app.entity.User;

public interface UserDao extends JpaRepository<User, Long> {

	Optional<User> findByEmail(String email);

	@Query("SELECT u From User u where u.role='ROLE_SELLER'")
	List<User> findAllSellers();

	@Query("SELECT u From User u where u.role='ROLE_CUSTOMER'")
	List<User> findAllCustomers();

}
