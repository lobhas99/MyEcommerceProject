package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.CartItemDTO;
import com.app.dto.OrderDTO;
import com.app.dto.PlaceOrderDTO;
import com.app.exception.ResourceNotFoundException;
import com.app.service.OrderService;


@Validated
@RestController
@RequestMapping("/customer/orders")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })

public class OrderController {
	@Autowired
	OrderService orderService;

	@PostMapping("/{Id}")
	public ResponseEntity<?> placeOrder(@PathVariable Long Id) throws ResourceNotFoundException {
		PlaceOrderDTO orderDto = orderService.placeOrder(Id);
		if (orderDto == null)
			return ResponseEntity.ok(new ApiResponse("Cart is Empty !!!"));

		return ResponseEntity.ok(orderDto);
	}

	@PutMapping("/{oId}")
	public ResponseEntity<?> cancelOrder(@PathVariable Long oId) throws ResourceNotFoundException {
		return ResponseEntity.ok(orderService.cancelOrder(oId));
	}

	@GetMapping("/{Id}")
	public ResponseEntity<?> getAllOrders(@PathVariable Long Id) {
		List<OrderDTO> list = orderService.getAllOrders(Id);
		if (!list.isEmpty())
			return ResponseEntity.ok(list);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("You have no orders yet!!"));
	}

	@GetMapping("/books/{oId}")
	public ResponseEntity<?> getAllBooksOfOrder(@PathVariable Long oId) {
		List<CartItemDTO> list = orderService.getAllOrderProducts(oId);
		if (!list.isEmpty())
			return ResponseEntity.ok(list);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("No products in order!!"));
	}
}