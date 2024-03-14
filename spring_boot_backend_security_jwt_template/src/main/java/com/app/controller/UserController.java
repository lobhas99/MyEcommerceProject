package com.app.controller;

import java.util.List;

import javax.mail.MessagingException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.UserDTO;
import com.app.entity.User;
import com.app.service.EmailService;
import com.app.service.OtpGenerator;
import com.app.service.UserService;

@RestController
@CrossOrigin(origins = "*",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	EmailService emailService;

	@Autowired
	OtpGenerator otpGenerator;

	@Autowired
	ModelMapper mapper;

	@GetMapping("/seller")
	public ResponseEntity<?> findAllSellers() {
		System.out.println("findAllSellers...");
		List<UserDTO> result = userService.findAllSellers();
		return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}

	@GetMapping("/seller/{id}")
	public ResponseEntity<?> findSellerById(@PathVariable("id") Long id) {
		System.out.println("findSellerById...");
		UserDTO result = userService.findById(id);
		if (result == null)
			return new ResponseEntity<>(new ApiResponse("Seller not found!!"), HttpStatus.ACCEPTED);
		return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}

	@GetMapping("/customer")
	public ResponseEntity<?> findAllCustomers() {
		System.out.println("findAllCustomers...");
		List<UserDTO> result = userService.findAllCustomers();
		return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}

	@GetMapping("/customer/{id}")
	public ResponseEntity<?> findCustomerById(@PathVariable("id") Long id) {
		System.out.println("findCustomerById...");
		UserDTO result = userService.findById(id);
		if (result == null)
			return new ResponseEntity<>(new ApiResponse("Customer not found!!"), HttpStatus.ACCEPTED);
		return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
	}

//	@PutMapping("{id}")
//	public ResponseEntity<?> updateProfile(@RequestBody User User, @PathVariable("id") int id) {
//		userService.updateProfile(User, id);
//		return Response.status(HttpStatus.OK);
//	}

	@PostMapping("/forgetpassword")
	public ResponseEntity<?> forgetPassword(@RequestBody UserDTO UserDTO) throws MessagingException {
		System.out.print("Sending OTP");
		String otp = otpGenerator.generateOTP();
		emailService.sendOtp(UserDTO.getEmail(), "OTP: " + otp, "OTP Verification! MyEcommerce Application! ");
		User user = userService.findByEmail(UserDTO.getEmail());
		if (user != null) {
			UserDTO userDto = new UserDTO();
			userDto.setOtp(otp);
			System.out.print(otp);
			userDto = mapper.map(user, UserDTO.class);
			return new ResponseEntity<>(userDto, HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity<>(new ApiResponse("User not found!!"), HttpStatus.OK);
	}

	@PutMapping("/resetpassword")
	public ResponseEntity<?> resetPassword(@RequestBody UserDTO UserDTO) throws MessagingException {
		User User = userService.findByEmail(UserDTO.getEmail());
		if (User != null) {
			userService.resetPassword(User, UserDTO.getPassword());
			return new ResponseEntity<>(HttpStatus.ACCEPTED);
		} else
			return new ResponseEntity<>(HttpStatus.OK);

	}

}
