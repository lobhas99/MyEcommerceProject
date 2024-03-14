package com.app.service;

import com.app.entity.Cart;
import com.app.entity.CartItems;
import com.app.entity.Product;
import com.app.exception.CartItemException;
import com.app.exception.UserException;

public interface CartItemService {
	public CartItems createCartItem(CartItems cartItem);

	public CartItems updateCartItem(Long userId, Long id, CartItems cartItem) throws CartItemException;

	public CartItems isCartItemExist(Cart cart, Product product, Long userId);

	public void removeCartItem(Long userId, Long cartItemId) throws UserException, CartItemException;

	public CartItems findCartItemById(Long cartItemId) throws CartItemException;

}
