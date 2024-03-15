package com.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.ApiResponse;
import com.app.dto.OrderDTO;
import com.app.enums.OrderStatus;
import com.app.exception.ResourceNotFoundException;
import com.app.service.OrderService;

@Validated
@RestController
@RequestMapping("/admin/orders")
@CrossOrigin(origins = "*", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE })

public class AdminOrderController {
	@Autowired
	OrderService orderService;

	
	@PutMapping("/{oStatus}/{oId}")
	public ResponseEntity<?> changeOrderStatus(@PathVariable OrderStatus oStatus, @PathVariable Long oId)
			throws ResourceNotFoundException {
		return ResponseEntity.ok(orderService.changeOrderStatus(oStatus, oId));
	}

	@GetMapping
	public ResponseEntity<?> getAll() {
		List<OrderDTO> list = orderService.getAll();
		if (!list.isEmpty())
			return ResponseEntity.ok(list);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse("No orders yet!!"));
	}
}