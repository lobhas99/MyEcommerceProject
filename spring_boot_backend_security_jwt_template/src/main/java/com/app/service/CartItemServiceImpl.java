package com.app.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CartItemsDao;
import com.app.dto.UserDTO;
import com.app.entity.Cart;
import com.app.entity.CartItems;
import com.app.entity.Product;
import com.app.exception.CartItemException;
import com.app.exception.UserException;

@Service
@Transactional
public class CartItemServiceImpl implements CartItemService {

	@Autowired
	private CartItemsDao cartItemsDao;

	@Autowired
	private UserService userService;

	@Override
	public CartItems createCartItem(CartItems cartItem) {
		cartItem.setQty(1);
		cartItem.setPrice(cartItem.getProduct().getPrice() * cartItem.getQty());
		return cartItemsDao.save(cartItem);
	}

	@Override
	public CartItems updateCartItem(Long userId, Long id, CartItems cartItem) throws CartItemException {

		CartItems item = findCartItemById(id);
		UserDTO user = userService.findById(item.getUserId());

		if (user.getId().equals(userId)) {

			item.setQty(cartItem.getQty());
			item.setPrice(item.getQty() * item.getProduct().getPrice());
			item.setDiscountedPrice(item.getQty() * item.getProduct().getDiscountedPrice());

			return cartItemsDao.save(item);
		} else
			throw new CartItemException("You Can't Update Another Cart");
	}

	@Override
	public CartItems isCartItemExist(Cart cart, Product product, Long userId) {
		return cartItemsDao.isCartItemExist(cart, product, userId);
	}

	@Override
	public void removeCartItem(Long userId, Long cartItemId) throws UserException, CartItemException {
		System.out.println("userId- " + userId + " cartItemId " + cartItemId);

		CartItems cartItem = findCartItemById(cartItemId);

		UserDTO user = userService.findById(cartItem.getUserId());
		UserDTO reqUser = userService.findById(userId);

		if (user.getId().equals(reqUser.getId()))
			cartItemsDao.deleteById(cartItem.getId());
		else
			throw new UserException("you can't remove another user's item");

	}

	@Override
	public CartItems findCartItemById(Long cartItemId) throws CartItemException {
		Optional<CartItems> opt = cartItemsDao.findById(cartItemId);
		if (opt.isPresent())
			return opt.get();
		throw new CartItemException("cartItem not found with id : " + cartItemId);

	}

}
