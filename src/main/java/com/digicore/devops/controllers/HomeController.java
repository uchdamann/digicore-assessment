package com.digicore.devops.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.digicore.devops.dtos.AccountQueryDTO;
import com.digicore.devops.dtos.ResponseDTO;
import com.digicore.devops.models.Transaction;
import com.digicore.devops.services.AccountServices;


@RestController
@ResponseStatus(HttpStatus.OK)
@RequestMapping("api/digicore/v1")
@CrossOrigin(maxAge = 3600, origins = "*")
public class HomeController {
	
	@Autowired
	private AccountServices accountServices;
	
	@GetMapping("/account-info/{accountNumber}")
	public ResponseDTO<AccountQueryDTO> queryAccount(@PathVariable String accountNumber){
		return accountServices.queryAccount(accountNumber);
	}
	
	@GetMapping("/account-statement/{accountNumber}")
	public ResponseDTO<List<Transaction>> getAccountStatement(@PathVariable String accountNumber){
		return accountServices.getAccountStatement(accountNumber);
	}
}