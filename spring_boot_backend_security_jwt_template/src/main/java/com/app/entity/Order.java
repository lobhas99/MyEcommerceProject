package com.app.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.app.enums.OrderStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order extends BaseEntity {

	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;

	@Column(name = "order_date")
	private LocalDate orderDate;

	@Column(name = "delivery_date")
	private LocalDate deliveryDate;

	@Column(name = "total_amount")
	private double totalAmount;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

//	@Embedded
//	private PaymentDetails paymentDetails;

}
