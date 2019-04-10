package com.telecom.service;

import java.util.List;
import java.util.Optional;

import com.telecom.model.Customer;
import com.telecom.model.PhoneNumber;

public interface PhoneNumberService {
	List<PhoneNumber> findByCustomer(Customer customer);
	Optional<PhoneNumber> findById(Integer number);
	List<PhoneNumber> findAll();
	void save(PhoneNumber phoneNumber);
}
