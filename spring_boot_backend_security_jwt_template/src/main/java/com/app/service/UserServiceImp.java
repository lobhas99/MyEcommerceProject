package com.app.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.dao.UserDao;
import com.app.dto.Signup;
import com.app.entity.User;

@Service
@Transactional
public class UserServiceImp implements UserService {

	@Autowired
	private UserDao userdao;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public Signup userRegisteration(Signup reqDTO) {
		User user=mapper.map(reqDTO,User.class);
		user.setPassword(encoder.encode(reqDTO.getPassword()));
		return mapper.map(userdao.save(user),Signup.class);
	}

}
