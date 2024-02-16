package org.jsp.customeradressapp.service;

import java.util.Optional;

import org.jsp.customeradressapp.dao.CustomerDao;
import org.jsp.customeradressapp.dto.Customer;
import org.jsp.customeradressapp.dto.ResponseStructure;
import org.jsp.customeradressapp.exception.IdNotFoundException;
import org.jsp.customeradressapp.exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {
	@Autowired
	public CustomerDao customerDao;

	public ResponseEntity<ResponseStructure<Customer>> saveCustomer(Customer customer) {
		ResponseStructure<Customer> structure = new ResponseStructure<>();
		structure.setData(customerDao.saveCustomer(customer));
		structure.setMessage("Customer saved");
		structure.setStatusCode(HttpStatus.CREATED.value());
		return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<Customer>> updateCustomer(Customer customer) {
		ResponseStructure<Customer> structure = new ResponseStructure<>();
		Optional<Customer> recCustomer = customerDao.findById(customer.getId());
		if (recCustomer.isPresent()) {
			Customer c = recCustomer.get();
			c.setAdress(customer.getAdress());
			c.setEmail(customer.getEmail());
			c.setName(customer.getName());
			c.setPassword(customer.getPassword());
			c.setPhone(customer.getPhone());
			structure.setData(customerDao.updateCustomer(customer));
			structure.setMessage("Customer Updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Customer>> findById(int id) {
		ResponseStructure<Customer> structure = new ResponseStructure<>();
		Optional<Customer> recCustomer = customerDao.findById(id);
		if (recCustomer.isPresent()) {
			structure.setData(recCustomer.get());
			structure.setMessage("Customer Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Customer>> verifyByPhone(long phone, String password) {
		ResponseStructure<Customer> structure = new ResponseStructure<>();
		Optional<Customer> recCustomer = customerDao.verifyByPhone(phone, password);
		if (recCustomer.isPresent()) {
			structure.setData(recCustomer.get());
			structure.setMessage("Verification Successfull");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialsException();
	}

	public ResponseEntity<ResponseStructure<Customer>> verifyByEmail(String email, String password) {
		ResponseStructure<Customer> structure = new ResponseStructure<>();
		Optional<Customer> recCustomer = customerDao.verifyByEmail(email, password);
		if (recCustomer.isPresent()) {
			structure.setData(recCustomer.get());
			structure.setMessage("Verification Successfull");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Customer>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialsException();
	}
}
