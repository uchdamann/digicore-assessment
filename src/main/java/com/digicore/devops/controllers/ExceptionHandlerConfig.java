package com.digicore.devops.controllers;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpStatus;

import com.digicore.devops.dtos.ResponseDTO;
import com.digicore.devops.exceptions.*;
import static com.digicore.devops.enums.ResponseMessages.*;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceptionHandlerConfig {
	
	
	@ExceptionHandler(LoginException.class)
	public ResponseDTO<String> handleLoginException(LoginException ex) {
		return ResponseDTO.newInstance(ERROR.getCode(), ERROR.getMessage(), ex.getMessage(), ERROR.getSuccess());
	}
	
	@ExceptionHandler(AccountDetailsException.class)
	public ResponseDTO<String> handleAccountDetailsException(AccountDetailsException ex) {
		return ResponseDTO.newInstance(ERROR.getCode(), ERROR.getMessage(), ex.getMessage(), ERROR.getSuccess());
	}
	
	@ExceptionHandler(InvalidAccountException.class)
	public ResponseDTO<String> handleInvalidAccountException(InvalidAccountException ex) {
		return ResponseDTO.newInstance(ERROR.getCode(), ERROR.getMessage(), ex.getMessage(), ERROR.getSuccess());
	}
	
	@ExceptionHandler(FinancialRestrictionException.class)
	public ResponseDTO<String> handleFinancialRestrictionException(FinancialRestrictionException ex) {
		return ResponseDTO.newInstance(ERROR.getCode(), ERROR.getMessage(), ex.getMessage(), ERROR.getSuccess());
	}
}