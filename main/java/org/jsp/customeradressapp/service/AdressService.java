package org.jsp.customeradressapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.customeradressapp.dao.AdressDao;
import org.jsp.customeradressapp.dao.CustomerDao;
import org.jsp.customeradressapp.dto.Adress;
import org.jsp.customeradressapp.dto.Customer;
import org.jsp.customeradressapp.dto.ResponseStructure;
import org.jsp.customeradressapp.exception.IdNotFoundException;
import org.jsp.customeradressapp.exception.InvalidCredentialsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AdressService {

	@Autowired
	private AdressDao adressDao;
	@Autowired
	private CustomerDao customerDao;

	public ResponseEntity<ResponseStructure<Adress>> saveAdress(Adress adress, int Cust_id) {
		ResponseStructure<Adress> structure = new ResponseStructure<>();
		Optional<Customer> recCustomer = customerDao.findById(Cust_id);
		if (recCustomer.isPresent()) {
			Customer c = recCustomer.get();
			c.getAdress().add(adress);
			customerDao.saveCustomer(c);
			adress.setCustomer(c);
			structure.setData(adressDao.saveAdress(adress));
			structure.setMessage("Adress Saved");
			structure.setStatusCode(HttpStatus.CREATED.value());
			return new ResponseEntity<ResponseStructure<Adress>>(structure, HttpStatus.CREATED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Adress>> updateAdress(Adress adress) {
		ResponseStructure<Adress> structure = new ResponseStructure<>();
		Optional<Adress> recAdress = adressDao.findById(adress.getId());
		if (recAdress.isPresent()) {
			Adress a = recAdress.get();
			a.setArea(adress.getArea());
			a.setCity(adress.getCity());
			a.setCountry(adress.getCountry());
			a.setLandmark(adress.getLandmark());
			a.setName(adress.getName());
			a.setPincode(adress.getPincode());
			a.setState(adress.getState());
			a.setType(adress.getType());
			structure.setData(adressDao.updateAdress(a)); //importance to be given
			structure.setMessage("Adress Updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			return new ResponseEntity<ResponseStructure<Adress>>(structure, HttpStatus.ACCEPTED);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<Adress>> findById(int id) {
		ResponseStructure<Adress> structure = new ResponseStructure<>();
		Optional<Adress> recAdress = adressDao.findById(id);
		if (recAdress.isPresent()) {
			structure.setData(recAdress.get());
			structure.setMessage("Adress Found");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<Adress>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<List<Adress>>> findAdressByCId(int id) {
		ResponseStructure<List<Adress>> structure = new ResponseStructure<>();
		Optional<List<Adress>> recAdress = adressDao.findByCId(id);
		if (recAdress.isPresent()) {
			structure.setData(recAdress.get());
			structure.setMessage("Adress Founded By Customer Id");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Adress>>>(structure, HttpStatus.OK);
		}
		throw new IdNotFoundException();
	}

	public ResponseEntity<ResponseStructure<List<Adress>>> findAdressByCPhoneAndPassword(long phone, String password) {
		ResponseStructure<List<Adress>> structure = new ResponseStructure<>();
		Optional<List<Adress>> recAdress = adressDao.findByCPhoneAndPassword(phone, password);
		if (recAdress.isPresent()) {
			structure.setData(recAdress.get());
			structure.setMessage("Adress Found Based On Customer Phone & Password");
			structure.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<ResponseStructure<List<Adress>>>(structure, HttpStatus.OK);
		}
		throw new InvalidCredentialsException();
	}
}
