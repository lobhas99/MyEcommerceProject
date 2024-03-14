package com.app.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.AddressDao;
import com.app.dao.OrderDao;
import com.app.dao.OrderItemDao;
import com.app.dao.UserDao;
import com.app.entity.Address;
import com.app.entity.Cart;
import com.app.entity.CartItems;
import com.app.entity.Order;
import com.app.entity.OrderItem;
import com.app.entity.User;
import com.app.enums.OrderStatus;
import com.app.enums.PaymentStatus;
import com.app.exception.OrderException;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDao orderDao;

	@Autowired
	private CartService cartService;

	@Autowired
	private AddressDao addressDao;

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private OrderItemDao orderItemDao;

	@Override
	public Order createOrder(User user, Address shippingAdress) {
		shippingAdress.setUser(user);
		Address address = addressDao.save(shippingAdress);
		user.getAddresses().add(address);
		userDao.save(user);

		Cart cart = cartService.findUserCart(user.getId());
		List<OrderItem> orderItems = new ArrayList<>();

		for (CartItems item : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();

			orderItem.setPrice(item.getPrice());
			orderItem.setProduct(item.getProduct());
			orderItem.setQuantity(item.getQty());
			orderItem.setUserId(item.getUserId());
			orderItem.setDiscountedPrice(item.getDiscountedPrice());

			OrderItem createdOrderItem = orderItemDao.save(orderItem);

			orderItems.add(createdOrderItem);
		}

		Order createdOrder = new Order();
		createdOrder.setUser(user);
		createdOrder.setOrderItems(orderItems);
		createdOrder.setTotalPrice(cart.getTotalPrice());
		createdOrder.setTotalDiscountedPrice(cart.getTotalDiscountedPrice());
		createdOrder.setDiscount(cart.getTotalDiscount());
		createdOrder.setTotalItems(cart.getTotalitems());

		createdOrder.setShippingAddress(address);
		createdOrder.setOrderDate(LocalDateTime.now());
		createdOrder.setOrderStatus(OrderStatus.PENDING);
		createdOrder.getPaymentDetails().setStatus(PaymentStatus.PENDING);
		createdOrder.setCreatedAt(LocalDateTime.now());

		Order savedOrder = orderDao.save(createdOrder);

		for (OrderItem item : orderItems) {
			item.setOrder(savedOrder);
			orderItemDao.save(item);
		}
		return savedOrder;
	}

	@Override
	public Order findOrderById(Long orderId) throws OrderException {
		Optional<Order> opt = orderDao.findById(orderId);

		if (opt.isPresent()) {
			return opt.get();
		}
		throw new OrderException("order not exist with id " + orderId);

	}

	@Override
	public List<Order> usersOrderHistory(Long userId) {
		List<Order> orders = orderDao.getUsersOrders(userId);
		return orders;
	}

	@Override
	public Order placedOrder(Long orderId) throws OrderException {
		Order order = findOrderById(orderId);
		order.setOrderStatus(OrderStatus.PLACED);
		order.getPaymentDetails().setStatus(PaymentStatus.COMPLETED);
		return order;
	}

	@Override
	public Order confirmedOrder(Long orderId) throws OrderException {
		Order order = findOrderById(orderId);
		order.setOrderStatus(OrderStatus.CONFIRMED);
		return orderDao.save(order);
	}

	@Override
	public Order shippedOrder(Long orderId) throws OrderException {
		Order order = findOrderById(orderId);
		order.setOrderStatus(OrderStatus.SHIPPED);
		return orderDao.save(order);
	}

	@Override
	public Order deliveredOrder(Long orderId) throws OrderException {
		Order order = findOrderById(orderId);
		order.setOrderStatus(OrderStatus.DELIVERED);
		return orderDao.save(order);
	}

	@Override
	public List<Order> getAllOrders() {
		return orderDao.findAllByOrderByCreatedAtDesc();
	}

	@Override
	public void deleteOrder(Long orderId) throws OrderException {
		Order order = findOrderById(orderId);
		if (order != null)
			orderDao.deleteById(orderId);
	}

	@Override
	public Order cancelledOrder(Long orderId) throws OrderException {
		Order order = findOrderById(orderId);
		order.setOrderStatus(OrderStatus.CANCELLED);
		return orderDao.save(order);
	}

}
