package com.digicore.devops.dtos;

import lombok.Getter;

@Getter
public class ResponseDTO<T> {	
	private final int responseCode;
	private final Boolean success;
	private final String message;
	private final T data;
	
	private ResponseDTO(int rCode, String message, T data, Boolean success) {
		this.responseCode=rCode;
		this.message=message;
		this.data=data;
		this.success=success;
	}
	
	public static <T> ResponseDTO<T> newInstance(int rCode, String message, T data, Boolean success){
		return new ResponseDTO<T>(rCode, message, data, success);
	}
}
