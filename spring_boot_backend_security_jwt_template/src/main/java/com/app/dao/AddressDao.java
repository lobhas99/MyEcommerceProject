package com.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Address;

public interface AddressDao extends JpaRepository<Address, Long>{

	public Optional<Address> findByUserId(Long id);

}
