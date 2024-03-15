package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.CartItems;

public interface CartItemsDao extends JpaRepository<CartItems, Long> {

	public List<CartItems> findByUserId(Long productId);

	public void deleteByUserIdAndProductId(Long cId, Long productId);

	public CartItems findByUserIdAndProductId(Long cId, Long productId);

}