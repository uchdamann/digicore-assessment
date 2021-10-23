package com.digicore.devops.exceptions;

import java.io.Serializable;

public class LoginException extends RuntimeException implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 850727962278959390L;
	private static final String MESSAGE="Invalid Login Credentials";
	
	public LoginException()
    {
        super(MESSAGE);
    }

    public LoginException(Throwable cause)
    {
        super(MESSAGE, cause);
    }

    public LoginException(String message)
    {
        super(message);
    }

    public LoginException(String message, Throwable cause)
    {
        super(message, cause);
    }

}
