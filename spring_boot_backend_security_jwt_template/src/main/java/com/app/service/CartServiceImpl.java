package com.app.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CartDao;
import com.app.dto.AddItemDTO;
import com.app.entity.Cart;
import com.app.entity.CartItems;
import com.app.entity.Product;
import com.app.entity.User;
import com.app.exception.ProductException;

@Service
@Transactional
public class CartServiceImpl implements CartService {
	@Autowired
	private CartDao cartDao;

	@Autowired
	private CartItemService cartItemService;

	@Autowired
	private ProductService productService;

	@Override
	public Cart createCart(User user) {
		Cart cart = new Cart();
		cart.setUser(user);
		return cartDao.save(cart);
	}

	@Override
	public CartItems addCartItem(Long userId, AddItemDTO req) throws ProductException {
		Cart cart = cartDao.findByUserId(userId);
		Product product = productService.findProductById(req.getProductId());

		CartItems isPresent = cartItemService.isCartItemExist(cart, product, userId);

		if (isPresent == null) {
			CartItems cartItem = new CartItems();
			cartItem.setProduct(product);
			cartItem.setCart(cart);
			cartItem.setQty(req.getQuantity());
			cartItem.setUserId(userId);

			int price = req.getQuantity() * product.getDiscountedPrice();
			cartItem.setPrice(price);

			CartItems createdCartItem = cartItemService.createCartItem(cartItem);
			cart.getCartItems().add(createdCartItem);
			return createdCartItem;
		}

		return isPresent;
	}

	@Override
	public Cart findUserCart(Long userId) {
		Cart cart = cartDao.findByUserId(userId);
		int totalPrice = 0;
		int totalDiscountedPrice = 0;
		int totalItem = 0;
		for (CartItems cartsItem : cart.getCartItems()) {
			totalPrice += cartsItem.getPrice();
			totalDiscountedPrice += cartsItem.getDiscountedPrice();
			totalItem += cartsItem.getQty();
		}

		cart.setTotalPrice(totalPrice);
		cart.setTotalitems(cart.getCartItems().size());
		cart.setTotalDiscountedPrice(totalDiscountedPrice);
		cart.setDiscountedPrice(totalPrice - totalDiscountedPrice);
		cart.setTotalitems(totalItem);

		return cartDao.save(cart);
	}

}
