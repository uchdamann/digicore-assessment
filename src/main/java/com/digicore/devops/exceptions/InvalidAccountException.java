package com.digicore.devops.exceptions;

import java.io.Serializable;

public class InvalidAccountException extends RuntimeException implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2014628954465711873L;
	private static final String MESSAGE = "Account number must be 10 digits";
	
	public InvalidAccountException() {
		super(MESSAGE);
	}
	
	public InvalidAccountException(Throwable cause) {
		super(cause);
	}
	
	public InvalidAccountException(String message) {
		super(message);
	}
	
	public InvalidAccountException(Throwable cause, String message) {
		super(message, cause);
	}

}
