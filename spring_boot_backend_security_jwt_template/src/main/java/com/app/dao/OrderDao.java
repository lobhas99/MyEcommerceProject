package com.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entity.Order;

public interface OrderDao extends JpaRepository<Order, Long> {

	List<Order> findByUserId(Long id);

}
