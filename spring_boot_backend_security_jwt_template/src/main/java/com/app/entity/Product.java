package com.app.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.app.enums.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long isbn;

	private String name;

	private String Author;

	private Integer price;

	private Category category;

	private String photo;
		
	@ManyToOne
	@JoinColumn(name = "seller_id")
	private User user;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	private String Description;

	@Column(name = "released_date")
	private LocalDate ReleasedDate;

	private Integer stock;
}
