package com.digicore.devops.exceptions;

import java.io.Serializable;

public class FinancialRestrictionException extends RuntimeException implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5439840174703967323L;
	private static final String MESSAGE="The amount you have chosen is not approved";
	
	public FinancialRestrictionException() {
		super(MESSAGE);
	}
	
	public FinancialRestrictionException(Throwable cause) {
		super(cause);
	}
	
	public FinancialRestrictionException(String message) {
		super(message);
	}
	
	public FinancialRestrictionException(String message, Throwable cause) {
		super(message, cause);
	}
}
