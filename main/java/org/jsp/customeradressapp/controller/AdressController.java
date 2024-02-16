package org.jsp.customeradressapp.controller;

import java.util.List;

import org.jsp.customeradressapp.dto.Adress;
import org.jsp.customeradressapp.dto.ResponseStructure;
import org.jsp.customeradressapp.service.AdressService;
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
public class AdressController {

	@Autowired
	private AdressService service;

	@PostMapping(value = "/add/{cust_id}")
	public ResponseEntity<ResponseStructure<Adress>> saveAdress(@RequestBody Adress adress,
			@PathVariable(name = "cust_id") int cust_id) {
		return service.saveAdress(adress, cust_id);
	}

	@PutMapping(value = "/add")
	public ResponseEntity<ResponseStructure<Adress>> updateAdress(@RequestBody Adress adress) {
		return service.updateAdress(adress);
	}

	@GetMapping(value = "/add/{id}")
	public ResponseEntity<ResponseStructure<Adress>> findById(@PathVariable(name = "id") int id) {
		return service.findById(id);
	}

	@GetMapping(value = "/add/cid/{id}")
	public ResponseEntity<ResponseStructure<List<Adress>>> FindAdressByCId(@PathVariable(name = "id") int id) {
		return service.findAdressByCId(id);
	}

	@PostMapping(value = "/add/cphone")
	public ResponseEntity<ResponseStructure<List<Adress>>> FindAdressByCPhoneAndPassword(@RequestParam long phone,
			@RequestParam String password) {
		return service.findAdressByCPhoneAndPassword(phone, password);
	}
}
