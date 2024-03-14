package com.app.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Cart extends BaseEntity {

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
	@Column(name = "cart_items")
	private Set<CartItems> cartItems = new HashSet<>();

	@Column(name = "total_price")
	private Integer totalPrice;

	@Column(name = "dicounted_price")
	private Integer discountedPrice;

	@Column(name = "total_items")
	private Integer totalitems;
	
	@Column(name = "total_discounted_price")
	private int totalDiscountedPrice;

	@Column(name = "total_discount")
	private int totalDiscount;

}
