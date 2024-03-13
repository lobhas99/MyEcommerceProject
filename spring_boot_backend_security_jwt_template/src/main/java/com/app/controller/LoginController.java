package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.SignInRequest;
import com.app.dto.SigninResponse;
import com.app.dto.Signup;
import com.app.security.JwtUtils;
import com.app.service.UserService;

@RestController
@RequestMapping("/users")
public class LoginController {

	@Autowired
	private UserService userService;

	@Autowired
	private JwtUtils utils;

	@Autowired
	private AuthenticationManager mgr;

	@PostMapping("/signup")
	public ResponseEntity<?> signUpUser(@RequestBody @Valid Signup reqDTO) {
		System.out.println(reqDTO);
		return ResponseEntity.status(HttpStatus.CREATED).body(userService.userRegisteration(reqDTO));
	}

	@PostMapping("/signin")
	public ResponseEntity<?> signInUser(@RequestBody @Valid SignInRequest reqDTO) {
		System.out.println(reqDTO);
		Authentication verifiedauth = mgr
				.authenticate(new UsernamePasswordAuthenticationToken(reqDTO.getEmail(), reqDTO.getPassword()));
		System.out.println(verifiedauth.getClass());
		return ResponseEntity.ok(new SigninResponse(utils.generateJwtToken(verifiedauth), "successful Authentication"));
	}

}
