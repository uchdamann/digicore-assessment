package com.digicore.devops.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ResponseMessages {
	ERROR(99, "Sorry an error occurred", false), 
	SUCCESS(100, "Transaction Successful", true);

	private int code;
	private String message;
	private Boolean success;

}
