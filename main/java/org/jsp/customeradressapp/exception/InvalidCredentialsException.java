package org.jsp.customeradressapp.exception;

public class InvalidCredentialsException extends RuntimeException {
	public String getMessage() {
		return "Invalid Credentials !!!";
	}
}
