package com.app.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dao.UserDao;
import com.app.dto.UserDTO;
import com.app.entity.User;

@Service
@Transactional
public class UserServiceImp implements UserService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private PasswordEncoder encoder;

	@Override
	public UserDTO userRegistration(UserDTO reqDTO) {
		User user = mapper.map(reqDTO, User.class);
		user.setPassword(encoder.encode(reqDTO.getPassword()));
		return mapper.map(userDao.save(user), UserDTO.class);
	}

	@Override
	public List<UserDTO> findAllAdmins() {
		List<User> users=userDao.findAllAdmins();
		return users.stream().map(u->mapper.map(u,UserDTO.class)).collect(Collectors.toList());
	}

	@Override
	public UserDTO findById(Long id) {
		User user = userDao.findById(id).orElse(null);
		if(user==null)
			return null;
		return mapper.map(user, UserDTO.class) ;
	}

	@Override
	public List<UserDTO> findAllCustomers() {
		List<User> users=userDao.findAllCustomers();
		return users.stream().map(u->mapper.map(u,UserDTO.class)).collect(Collectors.toList());
		}

//	@Override
//	public void updateProfile(UserDTO userDTO, int id) {
//		System.out.println("Updating profile..." + id);
//		Optional<User> user = userDao.findById(id);
//		if (user != null) {
//			if (userDTO.getPassword().equals("") || userDTO.getPassword() == null) {
//
//				userDTO.setPassword(user.get().getPassword());
//			} else if (userDTO.getPassword().equals(user.get().getPassword())) {
//				userDTO.setPassword(user.get().getPassword());
//			} else {
//				String encodedPassword = encoder.encode(userDTO.getPassword());
//				userDTO.setPassword(encodedPassword);
//			}
//			userDao.save(userDTO);
//		}
//	}

	@Override
	public User findByEmail(String email) {
		Optional<User> user = userDao.findByEmail(email);
		return user.get();
	}

	@Override
	public void resetPassword(User user, String password) {
		String encodedPassword = encoder.encode(password);
		user.setPassword(encodedPassword);
		userDao.save(user);
	}


}
