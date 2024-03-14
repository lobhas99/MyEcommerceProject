package com.app.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.OrderItemDao;
import com.app.entity.OrderItem;

@Service
@Transactional
public class OrderItemServiceImpl implements OrderItemService {

	@Autowired
	private OrderItemDao orderItemDao;

	@Override
	public OrderItem createOrderItem(OrderItem orderItem) {
		return orderItemDao.save(orderItem);
	}

}
