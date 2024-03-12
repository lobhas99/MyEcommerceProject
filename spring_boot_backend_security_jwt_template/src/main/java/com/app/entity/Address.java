package com.app.entity;

import javax.persistence.Column;
import javax.persistence.ManyToOne;

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
public class Address extends BaseEntity {

	private String name;

	@Column(name = "street_address")
	private String streetAddress;

	private String city;

	private String state;

	private String country;
	
	@Column(name = "pin_code")
	private String pinCode;

	@ManyToOne
	private User user;

	private String mobile_no;
}
