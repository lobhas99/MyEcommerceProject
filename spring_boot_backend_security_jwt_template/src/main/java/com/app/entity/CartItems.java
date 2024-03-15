package com.app.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public class CartItems extends BaseEntity {

	@ManyToOne
	private Product product;
	
	@Column(columnDefinition ="int default 1")
	private Integer quantity;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}
