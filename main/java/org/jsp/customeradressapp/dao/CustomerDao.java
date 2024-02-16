package org.jsp.customeradressapp.dao;

import java.util.Optional;

import org.jsp.customeradressapp.dto.Customer;
import org.jsp.customeradressapp.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CustomerDao {
	@Autowired
	private CustomerRepository repository;

	public Customer saveCustomer(Customer customer) {
		return repository.save(customer);
	}

	public Customer updateCustomer(Customer customer) {
		return repository.save(customer);
	}

	public Optional<Customer> findById(int id) {
		return repository.findById(id);
	}

	public Optional<Customer> verifyByPhone(long phone, String passwrd) {
		return repository.verify(phone, passwrd);
	}

	public Optional<Customer> verifyByEmail(String email, String password) {
		return repository.verify(email, password);
	}
}
