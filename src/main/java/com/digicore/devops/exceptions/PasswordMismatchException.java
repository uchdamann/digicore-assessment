package com.digicore.devops.exceptions;

import java.io.Serializable;

public class PasswordMismatchException extends RuntimeException implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -860140959504293679L;
	private static final String MESSAGE="Password is incorrect";
	
	public PasswordMismatchException()
    {
        super(MESSAGE);
    }
}
