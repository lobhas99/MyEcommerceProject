package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.app.entity.Cart;

public interface CartDao extends JpaRepository<Cart, Long> {

	@Query("select c from Cart where c.user.id=:id")
	Cart findByUserId(@Param("id") Long id);

}
