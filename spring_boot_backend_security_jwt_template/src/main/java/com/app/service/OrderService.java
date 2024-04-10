package com.app.service;

import java.util.List;

import com.app.dto.ApiResponse;
import com.app.dto.CartItemDTO;
import com.app.dto.OrderDTO;
import com.app.enums.OrderStatus;
import com.app.exception.ResourceNotFoundException;

public interface OrderService {

	OrderDTO placeOrder(Long cId) throws ResourceNotFoundException;

	ApiResponse cancelOrder(Long oId) throws ResourceNotFoundException;

	ApiResponse changeOrderStatus(OrderStatus oStatus, Long oId) throws ResourceNotFoundException;

	List<OrderDTO> getAllOrders(Long cId);

	List<CartItemDTO> getAllOrderProducts(Long oId);

	List<OrderDTO> getAll();

}
