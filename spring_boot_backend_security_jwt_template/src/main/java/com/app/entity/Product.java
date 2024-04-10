package com.app.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Lob;

import com.app.enums.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
public class Product extends BaseEntity {

	private String title;

	private Integer isbn;

	private String author;

	private Integer price;

	@Enumerated(EnumType.STRING)
	private Category category;

	private String description;
	
	@Lob
	private byte[] image;

	private Integer stock;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "released_date")
	private LocalDate releasedDate;

//	@Column(name = "num_ratings")
//	private int numRatings;

//	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<Rating> ratings = new ArrayList<>();
//
//	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<Review> reviews = new ArrayList<>();

}
