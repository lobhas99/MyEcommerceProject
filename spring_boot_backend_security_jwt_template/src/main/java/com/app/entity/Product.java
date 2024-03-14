package com.app.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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
public class Product extends BaseEntity {

	private String title;

	private Integer isbn;

	private String author;

	private Integer price;

	private Category category;
	
	private String description;

	private String photo;

	private Integer stock;

	@Column(name = "num_ratings")
	private int numRatings;

//	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<Rating> ratings = new ArrayList<>();
//
//	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
//	private List<Review> reviews = new ArrayList<>();

	private Integer discountedPrice;

	@ManyToOne
	@JoinColumn(name = "seller_id")
	private User user;

	@Column(name = "created_at")
	private LocalDateTime createdAt;

	@Column(name = "released_date")
	private LocalDate releasedDate;

}
