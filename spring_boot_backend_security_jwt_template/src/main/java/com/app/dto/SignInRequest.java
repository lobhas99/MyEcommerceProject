package com.app.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SignInRequest {

	@Email(message = "Invalid Email Format")
	private String email;

	@NotBlank
	@Length(min = 3, max = 20, message = "Invalid password Length")
	private String password;

}
