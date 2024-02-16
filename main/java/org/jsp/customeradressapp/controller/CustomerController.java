package org.jsp.customeradressapp.controller;

import org.jsp.customeradressapp.dto.Customer;
import org.jsp.customeradressapp.dto.ResponseStructure;
import org.jsp.customeradressapp.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CustomerController {
	@Autowired
	private CustomerService service;

	@PostMapping(value = "/cust")
	public ResponseEntity<ResponseStructure<Customer>> saveCustomer(@RequestBody Customer customer) {
		return service.saveCustomer(customer);
	}

	@PutMapping(value = "/cust")
	public ResponseEntity<ResponseStructure<Customer>> updateCustomer(@RequestBody Customer customer) {
		return service.updateCustomer(customer);
	}

	@GetMapping(value = "/cust/{id}")
	public ResponseEntity<ResponseStructure<Customer>> findById(@PathVariable(name = "id") int id) {
		return service.findById(id);
	}

	@PostMapping(value = "/cust/verify-by-phone")
	public ResponseEntity<ResponseStructure<Customer>> verifyByPhone(@RequestParam long phone,
			@RequestParam String password) {
		return service.verifyByPhone(phone, password);
	}

	@PostMapping(value = "/cust/verify-by-email")
	public ResponseEntity<ResponseStructure<Customer>> verifyByEmail(@RequestParam String email,
			@RequestParam String password) {
		return service.verifyByEmail(email, password);
	}
}
