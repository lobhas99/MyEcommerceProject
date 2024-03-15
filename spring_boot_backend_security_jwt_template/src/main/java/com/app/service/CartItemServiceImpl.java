package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CartItemsDao;
import com.app.dao.ProductDao;
import com.app.dao.UserDao;
import com.app.dto.ApiResponse;
import com.app.dto.CartItemDTO;
import com.app.dto.CartItemQtyDTO;
import com.app.entity.CartItems;
import com.app.entity.Product;
import com.app.entity.User;

@Service
@Transactional
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	private UserDao userDao;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private CartItemsDao cartItemsDao;

	@Autowired
	private ModelMapper mapper;

	@Override
	public ApiResponse addToCart(@Valid Long userId, @Valid Long productId) {

		CartItems cartItems = cartItemsDao.findByUserIdAndProductId(userId, productId);

		if (cartItems != null)
			return new ApiResponse("Product is already present in the cart");

		User user = userDao.getReferenceById(userId);
		Product product = productDao.getReferenceById(productId);
		CartItems cartItem = new CartItems();
		cartItem.setProduct(product);
		cartItem.setQuantity(1);
		cartItem.setUser(user);
		cartItemsDao.save(cartItem);

		return new ApiResponse("Product added to your cart");
	}

	@Override
	public List<CartItemDTO> displayCart(@Valid Long userId) {

		return cartItemsDao.findByUserId(userId).stream().map(cartItem -> mapper.map(cartItem, CartItemDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public ApiResponse removeFromCart(@Valid Long userId, @Valid Long productId) {

		cartItemsDao.deleteByUserIdAndProductId(userId, productId);

		return new ApiResponse("Product removed from the cart");
	}

	@Override
	public ApiResponse setQuantity(@Valid CartItemQtyDTO quantity) {

		CartItems cartItems = cartItemsDao.findByUserIdAndProductId(quantity.getUserId(), quantity.getProductId());
		cartItems.setQuantity(quantity.getQuantity());
		return new ApiResponse("Quantity Set");
	}

}
