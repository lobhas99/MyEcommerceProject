package com.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItems extends BaseEntity {

	@ManyToOne
	private Cart cart;

	@ManyToOne
	private Product product;

	private Integer qty;

	private Integer price;

	@Column(name="dicounted_price")
	private Integer discountedPrice;

	
}