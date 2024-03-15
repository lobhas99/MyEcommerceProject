package com.app.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.CartItemsDao;
import com.app.dao.OrderDao;
import com.app.dao.OrderItemDao;
import com.app.dao.ProductDao;
import com.app.dao.UserDao;
import com.app.dto.ApiResponse;
import com.app.dto.CartItemDTO;
import com.app.dto.OrderDTO;
import com.app.dto.PlaceOrderDTO;
import com.app.entity.CartItems;
import com.app.entity.Order;
import com.app.entity.OrderItem;
import com.app.enums.OrderStatus;
import com.app.exception.ResourceNotFoundException;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private CartItemsDao cartItemsDao;

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private UserDao userDao;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private OrderItemDao orderItemDao;

	@Autowired
	private ProductDao productDao;

	@Autowired
	private EmailService emailService;

	@Override
	public PlaceOrderDTO placeOrder(Long id) throws ResourceNotFoundException {
		List<CartItems> cartItems = cartItemsDao.findByUserId(id);
		if (cartItems.isEmpty())
			return null;
		Order order = orderDao.save(new Order(OrderStatus.CONFIRMED, LocalDate.now(), LocalDate.now().plusDays(5),
				0, userDao.getReferenceById(id)));
		List<OrderItem> orderItem = new ArrayList<OrderItem>();
		cartItems.forEach(c -> orderItem.add(new OrderItem( c.getProduct(), c.getQuantity(), order)));
		double total = 0;
		for (CartItems cartItem : cartItems) {

			total += (cartItem.getProduct().getPrice() * cartItem.getQuantity());
			if (cartItem.getProduct().getStock() >= cartItem.getQuantity())
				productDao.updateProductStock(cartItem.getProduct().getId(), -cartItem.getQuantity());
			else {
				throw new ResourceNotFoundException("Insufficient stock available for : " + cartItem.getProduct().getTitle()
						+ "STOCK LEFT : " + cartItem.getProduct().getStock());
			}
		}
		order.setTotalAmount(total);
		orderItemDao.saveAll(orderItem);
		cartItemsDao.deleteAll(cartItems);
		PlaceOrderDTO orderDto = mapper.map(order, PlaceOrderDTO.class);
		return orderDto;
	}

	@Override
	public ApiResponse cancelOrder(Long oId) throws ResourceNotFoundException {
		Order order = orderDao.findById(oId)
				.orElseThrow(() -> new ResourceNotFoundException("Order/OrderId does not exists !!"));
		if (order.getOrderStatus() == OrderStatus.CANCELLED)
			return new ApiResponse("Order(" + oId + ") has already " + "CANCELLED");
		List<OrderItem> orderQtys = orderItemDao.findByOrderId(oId);
		for (OrderItem orderQty : orderQtys) {
			productDao.updateProductStock(orderQty.getProduct().getId(), orderQty.getQuantity());
		}
		order.setOrderStatus(OrderStatus.CANCELLED);
		return new ApiResponse("Order(" + oId + ") has been CANCELLED");
	}

	@Override
	public ApiResponse changeOrderStatus(OrderStatus oStatus, Long oId) throws ResourceNotFoundException {
		Order order = orderDao.findById(oId)
				.orElseThrow(() -> new ResourceNotFoundException("Order/OrderId does not exists !!"));
		if (order.getOrderStatus() == OrderStatus.CANCELLED)
			return new ApiResponse("Order(" + oId + ") is a CANCELLED order!");
		if (oStatus == OrderStatus.CANCELLED)
			return cancelOrder(oId);
		order.setOrderStatus(oStatus);
		return new ApiResponse("Order(" + oId + ") status changed to " + oStatus.toString());
	}


	@Override
	public List<OrderDTO> getAllOrders(Long cId) {
		return orderDao.findByUserId(cId).stream().map(order -> mapper.map(order, OrderDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<CartItemDTO> getAllOrderProducts(Long oId) {
		return orderItemDao.findByOrderId(oId).stream().map(orderItem -> mapper.map(orderItem, CartItemDTO.class))
				.collect(Collectors.toList());
	}

	@Override
	public List<OrderDTO> getAll() {
		return orderDao.findAll().stream().map(order -> mapper.map(order, OrderDTO.class)).collect(Collectors.toList());
	}

}
