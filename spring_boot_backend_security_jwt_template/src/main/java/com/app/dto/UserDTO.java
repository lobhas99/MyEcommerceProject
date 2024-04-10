
package com.app.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.app.enums.Role;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {

	@JsonProperty(access = Access.READ_ONLY)
	private Long id;

	@NotBlank(message = "First Name required")
	private String firstName;

	private String lastName;

	@Email(message = "Invalid Email!!!")
	private String email;

	@JsonProperty(access = Access.WRITE_ONLY)
	private String password;
	
	private String mobileNo;

	private Role role;

	private LocalDate birthDate;

	private LocalDateTime createdTimestamp;

	private String otp;

}
