package org.jsp.customeradressapp.exception;

public class IdNotFoundException extends RuntimeException{
	public String getMessage() {
		return "Invalid Id !!!";
	}
}
