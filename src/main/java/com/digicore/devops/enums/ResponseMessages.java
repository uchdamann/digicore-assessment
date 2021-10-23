package com.digicore.devops.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ResponseMessages {
	ERROR(99, "Sorry an error occurred", false), 
	SUCCESS(100, "Transaction Successful", true),
	MINIMUM_OPENING_BALANCE(112, "Your initial deposit cannot be less than ", false);

	private int code;
	private String message;
	private Boolean success;

}
