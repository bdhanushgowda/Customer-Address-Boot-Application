package org.jsp.customeradressapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.customeradressapp.dto.Adress;
import org.jsp.customeradressapp.repository.AdressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AdressDao {
	@Autowired
	private AdressRepository repository;

	public Adress saveAdress(Adress adress) {
		return repository.save(adress);
	}

	public Adress updateAdress(Adress adress) {
		return repository.save(adress);
	}

	public Optional<Adress> findById(int id) {
		return repository.findById(id);
	}

	public Optional<List<Adress>> findByCId(int id) {
		return repository.findByCId(id);
	}

	public Optional<List<Adress>> findByCPhoneAndPassword(long phone, String password) {
		return repository.findByCPhoneAndPassword(phone, password);
	}
}
