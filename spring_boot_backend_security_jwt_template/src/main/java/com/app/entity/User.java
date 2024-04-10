package com.app.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.app.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User extends BaseEntity {

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;
	
	@Column(name = "email", unique = true, length = 25)
	private String email;

	@Column(name = "password", length = 100, nullable = false) 
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(name = "role", length = 20)
	private Role role;

	@Column(name = "mobile_no", length = 10)
	private String mobileNo;

	@Column(name = "birth_date")
	private LocalDate birthDate;

	@Column(name = "created_at")
	private LocalDateTime createdAt;
	
//	
//	@JsonIgnore
//	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<Review> review = new ArrayList<>();
//	
//	@JsonIgnore
//	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<Rating> rating = new ArrayList<>();

//	@Embedded
//	@ElementCollection
//	@CollectionTable(name = "payment_information")
//	private List<PaymentInformation> paymentInformation=new ArrayList<>();

}
