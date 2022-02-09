package com.eshope.webservice.exception;

public class InvalidOrder extends RuntimeException {

	
	private static final long serialVersionUID = 1L;

	public InvalidOrder(String message) {
		super(message);
	}
}
