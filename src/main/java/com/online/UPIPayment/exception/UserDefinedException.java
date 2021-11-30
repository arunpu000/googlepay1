package com.online.UPIPayment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserDefinedException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	private String message;

	public UserDefinedException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
