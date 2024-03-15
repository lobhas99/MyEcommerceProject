package com.app.service;

import java.util.List;

import javax.validation.Valid;

import com.app.dto.ApiResponse;
import com.app.dto.CartItemDTO;
import com.app.dto.CartItemQtyDTO;

public interface CartItemService {

	ApiResponse addToCart(@Valid Long userId, @Valid Long productId);
	
	List<CartItemDTO> displayCart(@Valid Long userId);
	
	ApiResponse removeFromCart(@Valid Long userId, @Valid Long productId);

	ApiResponse setQuantity(@Valid CartItemQtyDTO quantity);

}
