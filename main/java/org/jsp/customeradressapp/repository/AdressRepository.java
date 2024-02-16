package org.jsp.customeradressapp.repository;

import java.util.List;
import java.util.Optional;

import org.jsp.customeradressapp.dto.Adress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdressRepository extends JpaRepository<Adress, Integer> {

	@Query("select a from Adress a where a.customer.id=?1")
	public Optional<List<Adress>> findByCId(int id);

	@Query("select a from Adress a where a.customer.phone=?1 and a.customer.password=?2")
	public Optional<List<Adress>> findByCPhoneAndPassword(long phone, String password);
}
