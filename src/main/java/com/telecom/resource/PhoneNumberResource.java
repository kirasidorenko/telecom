package com.telecom.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telecom.model.Customer;
import com.telecom.model.PhoneNumber;
import com.telecom.service.CustomerService;
import com.telecom.service.PhoneNumberService;

@RestController
@RequestMapping("/api")
public class PhoneNumberResource {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private PhoneNumberService phoneNumberService;

	/**
	 * Get all phone numbers.
	 *
	 * @return the list
	 */
	@GetMapping("/phonenumbers")
	public List<PhoneNumber> getAllPhoneNumbers() {
		List<PhoneNumber> phoneNumbers = phoneNumberService.findAll();
		return phoneNumbers;
	}
	
	/**
	 * Get one phone number.
	 *
	 * @return the number
	 * @throws Exception 
	 */
	@GetMapping("/phonenumbers/{number}")
	public PhoneNumber getPhoneNumber(@PathVariable(value = "number") Integer number) throws Exception {
		Optional<PhoneNumber> phoneNumber = phoneNumberService.findById(number);
		if (!phoneNumber.isPresent()) {
			throw new Exception("Phone number not found [ " + number + " ]");
		}
		return phoneNumber.get();
	}

	/**
	 * Gets phone numbers by customer name.
	 *
	 * @param customerEmail
	 *            the customer email - id of customer
	 * @return the phone numbers by customer email
	 * @throws Exception
	 *             the resource not found exception
	 */
	@GetMapping("/customers/{email}/phonenumbers")
	public List<PhoneNumber> getPhoneNumbersByCustomer(
			@PathVariable(value = "email") String customerEmail) throws Exception {
		Optional<Customer> customer = customerService.findByEmail(customerEmail);
		if (!customer.isPresent()) {
			throw new Exception("Customer not found [ " + customerEmail + " ]");
		}
			
		return phoneNumberService.findByCustomer(customer.get());
	}

	/**
	 * Activate phone number.
	 *
	 * @param number
	 *            the phone number
	 * @return the response entity
	 * @throws Exception
	 *             the resource not found exception
	 */
	@PutMapping("/phonenumbers/{number}/activation")
	public ResponseEntity<?> activatePhoneNumber(@PathVariable(value = "number") Integer number) throws Exception {
		Optional<PhoneNumber> phoneNumberOptional = phoneNumberService.findById(number);
		if (!phoneNumberOptional.isPresent()) {
			throw new Exception("Phone Number not found [ " + number + " ]");
		}
		PhoneNumber phoneNumber = phoneNumberOptional.get();
		phoneNumber.setActive(true);
		phoneNumberService.save(phoneNumber);
		return ResponseEntity.ok().build();
	}
}