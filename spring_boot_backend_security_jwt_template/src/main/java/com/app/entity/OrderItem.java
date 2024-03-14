package com.app.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class OrderItem extends BaseEntity {

	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	@OneToOne
	@JoinColumn(name="product_id")
	private Product product;

	private Integer quantity;

	@Column(name = "discounted_price")
	private Integer discountedPrice;

	private Integer price;

	@Column(name = "delivery_date")
	private LocalDateTime deliveryDate;
	
	@Column(name = "user_id")
	private Long userId;

}
