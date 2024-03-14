package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entity.Cart;
import com.app.entity.CartItems;
import com.app.entity.Product;

public interface CartItemsDao extends JpaRepository<CartItems, Long>{

	@Query("SELECT ci from CartItem ci where ci.cart=:cart and ci.product=:product and ci.userId=:userId")
	public CartItems isCartItemExist(@Param("cart")Cart cart,@Param("product")Product product, @Param("userId")Long userId);
	
}