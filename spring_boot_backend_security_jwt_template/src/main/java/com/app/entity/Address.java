package com.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapKey;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

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
@Entity
public class Address extends BaseEntity {

	private String name;

	@Column(name = "street_address")
	private String streetAddress;

	private String city;

	private String state;
	
	private String country;

	@Column(name = "pin_code")
	private String pinCode;

	@Column(name = "mobile_no")
	private String mobileNumber;
	
	@OneToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="user_id")
	@MapsId
	private User user;

	
}
