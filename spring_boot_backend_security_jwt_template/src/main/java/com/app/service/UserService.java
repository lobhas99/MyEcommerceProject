package com.app.service;

import java.util.List;
import java.util.Optional;

import com.app.dto.UserDTO;
import com.app.entity.User;

public interface UserService {

	UserDTO userRegistration(UserDTO reqDTO);

	User findByEmail(String email);

	void resetPassword(User user, String password);

	UserDTO findById(Long id);

	List<UserDTO> findAllSellers();

	List<UserDTO> findAllCustomers();

}
