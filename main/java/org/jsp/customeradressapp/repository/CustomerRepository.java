package org.jsp.customeradressapp.repository;

import java.util.Optional;

import org.jsp.customeradressapp.dto.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
	
	@Query("select c from Customer c where c.phone=?1 and c.password=?2")
	public Optional<Customer> verify(long phone, String password);
	
	@Query("select c from Customer c where c.email=?1 and c.password=?2")
	public Optional<Customer> verify(String email, String password);
}
