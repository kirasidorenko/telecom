package com.telecom.service;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.telecom.Application;
import com.telecom.model.Customer;
import com.telecom.service.CustomerService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CustomerServiceTest {
	
	@Autowired
	private CustomerService customerService;
	
	@Test
	public void testFindByEmail(){	
		Optional<Customer> customer = customerService.findByEmail("flash@gordon.com");
		Assert.assertTrue("Flash Gordon".equals(customer.get().getName()));
	}
}
