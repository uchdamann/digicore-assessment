package com.digicore.devops.controllers;

import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;

import com.digicore.devops.dtos.ResponseDTO;
import com.digicore.devops.exceptions.*;
import static com.digicore.devops.enums.ResponseMessages.*;

import java.util.HashMap;
import java.util.Map;

import javax.validation.UnexpectedTypeException;

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
	
	@ExceptionHandler(TokenNotFoundException.class)
	public ResponseDTO<String> handleTokenNotFoundException(TokenNotFoundException ex) {
		return ResponseDTO.newInstance(ERROR.getCode(), ERROR.getMessage(), ex.getMessage(), ERROR.getSuccess());
	}

	@ExceptionHandler(FinancialRestrictionException.class)
	public ResponseDTO<String> handleFinancialRestrictionException(FinancialRestrictionException ex) {
		return ResponseDTO.newInstance(ERROR.getCode(), ERROR.getMessage(), ex.getMessage(), ERROR.getSuccess());
	}
	
	@ExceptionHandler(PasswordMismatchException.class)
	public ResponseDTO<String> handlePasswordMismatchException(PasswordMismatchException ex) {
		return ResponseDTO.newInstance(ERROR.getCode(), ERROR.getMessage(), ex.getMessage(), ERROR.getSuccess());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseDTO<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();

		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});

		return ResponseDTO.newInstance(ERROR.getCode(), ERROR.getMessage(), errors, ERROR.getSuccess());
	}

	@ExceptionHandler(HttpClientErrorException.class)
	public ResponseDTO<Map<String, String>> handleHttpExceptions(HttpClientErrorException ex) {
		Map<String, String> errors = new HashMap<>();
		errors.put("error2", ex.getMessage());

		return ResponseDTO.newInstance(ERROR.getCode(), ERROR.getMessage(), errors, ERROR.getSuccess());
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseDTO<String> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex) {
		String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();
		return ResponseDTO.newInstance(ERROR.getCode(), ERROR.getMessage(), error, ERROR.getSuccess());
	}

	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseDTO<String> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
		StringBuilder builder = new StringBuilder();
		builder.append(ex.getMethod());
		builder.append("method is not supported for this request. Supported methods are ");
		ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));
		return ResponseDTO.newInstance(ERROR.getCode(), ERROR.getMessage(), builder.toString(), ERROR.getSuccess());
	}

	@ExceptionHandler(UnexpectedTypeException.class)
	public ResponseDTO<String> handleUnexpectedTypeException(UnexpectedTypeException ex) {
		return ResponseDTO.newInstance(ERROR.getCode(), ERROR.getMessage(), ex.getMessage(), ERROR.getSuccess());
	}
}