package com.app.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.CartItemQtyDTO;
import com.app.service.CartItemService;

@RestController
@RequestMapping("/cartitem")
@Validated
@CrossOrigin(origins = "*",methods = {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class CartItemController {

	@Autowired
	private CartItemService cartItemService;

	@PostMapping("/cart/{Id}/{productId}")
	public ResponseEntity<?> addtoCart(@PathVariable @Valid Long Id, @PathVariable @Valid Long productId) {
		return ResponseEntity.status(HttpStatus.CREATED).body(cartItemService.addToCart(Id, productId));
	}

	@GetMapping("/cart/{Id}")
	public ResponseEntity<?> customerCart(@PathVariable @Valid Long Id) {
		return ResponseEntity.status(HttpStatus.OK).body(cartItemService.displayCart(Id));
	}

	@DeleteMapping("/cart/{Id}/{productId}")
	public ResponseEntity<?> removeFromCart(@PathVariable @Valid Long Id, @PathVariable @Valid Long productId) {
		return ResponseEntity.status(HttpStatus.OK).body(cartItemService.removeFromCart(Id, productId));
	}

	@PutMapping("/cart/qty")
	public ResponseEntity<?> setQuantity(@RequestBody @Valid CartItemQtyDTO cartItems) {
		System.out.println(cartItems);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(cartItemService.setQuantity(cartItems));
	}
}
