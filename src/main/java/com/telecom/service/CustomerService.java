package com.telecom.service;

import java.util.Optional;

import com.telecom.model.Customer;

public interface CustomerService {
	Optional<Customer> findByEmail(String email);
}
