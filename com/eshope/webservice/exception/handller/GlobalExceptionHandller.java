package com.eshope.webservice.exception.handller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.eshope.webservice.exception.InvalidProduct;
import com.eshope.webservice.exception.OrderNotFound;
import com.eshope.webservice.exception.ProductNotFound;
import com.eshope.webservice.exception.RollNotFound;
import com.eshope.webservice.exception.UserNotFound;

@ControllerAdvice
public class GlobalExceptionHandller {

	ExceptionResponse response;

	@ExceptionHandler(value = ProductNotFound.class)
	public ResponseEntity<ExceptionResponse> productNotFound(ProductNotFound exception) {
		response = new ExceptionResponse(exception.getMessage(), new Date(), HttpStatus.NOT_FOUND.name(),
				exception.getClass().getSimpleName());
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = InvalidProduct.class)
	public ResponseEntity<ExceptionResponse> invalidProduct(ProductNotFound exception) {
		response = new ExceptionResponse(exception.getMessage(), new Date(), HttpStatus.NOT_FOUND.name(),
				exception.getClass().getSimpleName());
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = UserNotFound.class)
	public ResponseEntity<ExceptionResponse> userNotFound(UserNotFound exception) {
		response = new ExceptionResponse(exception.getMessage(), new Date(), HttpStatus.NOT_FOUND.name(),
				exception.getClass().getSimpleName());
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = OrderNotFound.class)
	public ResponseEntity<ExceptionResponse> orderNotFound(OrderNotFound exception) {
		response = new ExceptionResponse(exception.getMessage(), new Date(), HttpStatus.NOT_FOUND.name(),
				exception.getClass().getSimpleName());
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(value = RollNotFound.class)
	public ResponseEntity<ExceptionResponse> rollNotFound(RollNotFound exception) {
		response = new ExceptionResponse(exception.getMessage(), new Date(), HttpStatus.NOT_FOUND.name(),
				exception.getClass().getSimpleName());
		return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
	}
}
