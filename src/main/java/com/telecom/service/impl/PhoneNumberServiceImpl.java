package com.telecom.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.telecom.model.Customer;
import com.telecom.model.PhoneNumber;
import com.telecom.repository.PhoneNumberRepository;
import com.telecom.service.PhoneNumberService;

@Service
public class PhoneNumberServiceImpl implements PhoneNumberService {

	@Autowired
	private PhoneNumberRepository phoneNumberRepository;
	
	@Override
	public List<PhoneNumber> findByCustomer(Customer customer) {
		return phoneNumberRepository.findByCustomer(customer);
	}

	@Override
	public Optional<PhoneNumber> findById(Integer number) {
		return phoneNumberRepository.findById(number);
	}

	@Override
	public List<PhoneNumber> findAll() {
		return phoneNumberRepository.findAll();
	}

	@Transactional
	@Override
	public void save(PhoneNumber phoneNumber) {
		phoneNumberRepository.save(phoneNumber);
	}

}
