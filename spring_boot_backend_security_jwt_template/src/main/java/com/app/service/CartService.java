package com.app.service;

import com.app.dto.AddItemDTO;
import com.app.entity.Cart;
import com.app.entity.CartItems;
import com.app.entity.User;
import com.app.exception.ProductException;

public interface CartService {

	public Cart createCart(User user);

	public Cart findUserCart(Long userId);

	CartItems addCartItem(Long userId, AddItemDTO req) throws ProductException;

}
