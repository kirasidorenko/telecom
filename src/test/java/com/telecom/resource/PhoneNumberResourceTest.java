package com.telecom.resource;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestClientException;

import com.telecom.Application;
import com.telecom.model.PhoneNumber;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PhoneNumberResourceTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	private String getRootUrl() {
		return "http://localhost:" + port + "/api";
	}

	//validate context
	@Test
	public void contextLoads() {
	}

	//get all phone numbers
	@Test
	public void testGetAllPhoneNumbers() {
		ResponseEntity<List<PhoneNumber>> response = restTemplate.exchange(
				getRootUrl() + "/phonenumbers",
				HttpMethod.GET, 
				null, 
				new ParameterizedTypeReference<List<PhoneNumber>>() {});
		List<PhoneNumber> numbers = response.getBody();
		Assert.assertTrue(numbers.size() == 6);
	}

	//get phone numbers by customer id
	@Test
	public void testGetPhoneNumbersByCustomerName() {
		ResponseEntity<List<PhoneNumber>> response = restTemplate.exchange(
				getRootUrl() + "/customers/john.citizen@gmail.com/phonenumbers", 
				HttpMethod.GET, 
				null,
				new ParameterizedTypeReference<List<PhoneNumber>>() {});
		List<PhoneNumber> numbers = response.getBody();
		Assert.assertTrue(numbers.size() == 2);
	}

	//activate phone number
	@Test
	public void testActivatePhoneNumber() {
		int number = 321;
		PhoneNumber phoneNumberBeforeActivation = restTemplate.getForObject(
				getRootUrl() + "/phonenumbers/" + number,
				PhoneNumber.class);
		Assert.assertTrue(!phoneNumberBeforeActivation.isActive());
		
		restTemplate.put(getRootUrl() + "/phonenumbers/" + number + "/activation", null);
		PhoneNumber phoneNumberAfterActivation = restTemplate.getForObject(
				getRootUrl() + "/phonenumbers/" + number,
				PhoneNumber.class);
		Assert.assertTrue(phoneNumberAfterActivation.isActive());
	}
	
	//negative test case
	@Test(expected = RestClientException.class)
	public void testGetPhoneNumbersByWrongCustomerName() {
		ResponseEntity<List<PhoneNumber>> response = restTemplate.exchange(
				getRootUrl() + "/customers/john.wayne@gmail.com/phonenumbers", 
				HttpMethod.GET, 
				null,
				new ParameterizedTypeReference<List<PhoneNumber>>() {});
		List<PhoneNumber> numbers = response.getBody();
		Assert.assertTrue(numbers.size() == 0);
	}
}
