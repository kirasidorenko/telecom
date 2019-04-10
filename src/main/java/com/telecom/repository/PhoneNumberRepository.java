package com.telecom.repository;

import com.telecom.model.Customer;
import com.telecom.model.PhoneNumber;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhoneNumberRepository extends JpaRepository<PhoneNumber, Integer> {
	List<PhoneNumber> findByCustomer(Customer customer);
}
