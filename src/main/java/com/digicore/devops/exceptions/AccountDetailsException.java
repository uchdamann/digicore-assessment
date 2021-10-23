package com.digicore.devops.exceptions;

import java.io.Serializable;

public class AccountDetailsException extends RuntimeException implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3801492962288172716L;
	private static final String MESSAGE="Account Details Not Found";
	
	public AccountDetailsException()
    {
        super(MESSAGE);
    }

    public AccountDetailsException(Throwable cause)
    {
        super(MESSAGE, cause);
    }

    public AccountDetailsException(String message)
    {
        super(message);
    }

    public AccountDetailsException(String message, Throwable cause)
    {
        super(message, cause);
    }

}
