package com.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.entity.Address;

public interface AddressDao extends JpaRepository<Address, Long>{

}
