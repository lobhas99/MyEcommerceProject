package com.app.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Review extends BaseEntity {

	public String review;

	@ManyToOne
	@JoinColumn(name = "user_id")
	public User user;

	@ManyToOne
	@JoinColumn(name = "product_id")
	public Product product;

	@Column(name="created_at")
	public LocalDate createdAt;
}
