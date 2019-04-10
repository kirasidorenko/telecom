package com.telecom.service;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.telecom.Application;
import com.telecom.model.Customer;
import com.telecom.model.PhoneNumber;
import com.telecom.service.PhoneNumberService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class PhoneNumberServiceTest {

	@Autowired
	private PhoneNumberService phoneNumberService;
	
	@Test
	public void testFindByCustomer(){
		Customer customer = new Customer();
		customer.setEmail("flash@gordon.com");
		customer.setName("Flash Gordon");
		
		List<PhoneNumber> list = phoneNumberService.findByCustomer(customer);
		Assert.assertTrue(list.size() > 0);
	}
	
	@Test
	public void testFindById(){
		Optional<PhoneNumber> number = phoneNumberService.findById(123456);
		Assert.assertTrue(number.isPresent());
	}
	
	@Test
	public void testFindAll(){
		List<PhoneNumber> list = phoneNumberService.findAll();
		Assert.assertTrue(list.size() > 0);
	}
	
	@Test
	public void testSave(){
		Customer customer = new Customer();
		customer.setEmail("flash@gordon.com");
		customer.setName("Flash Gordon");
		
		PhoneNumber phoneNumber = new PhoneNumber();
		phoneNumber.setNumber(1000);
		phoneNumber.setActive(true);
		phoneNumber.setCustomer(customer);
		
		phoneNumberService.save(phoneNumber);
	}
}
