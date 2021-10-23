package com.digicore.devops.exceptions;

import java.io.Serializable;

public class TokenNotFoundException extends RuntimeException implements Serializable{
/**
	 * 
	 */
	private static final long serialVersionUID = 6198546767743678688L;
private static final String MESSAGE="No token found in header";
	
	public TokenNotFoundException()
    {
        super(MESSAGE);
    }

}
