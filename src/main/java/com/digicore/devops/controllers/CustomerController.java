package com.digicore.devops.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.digicore.devops.dtos.AccountQueryDTO;
import com.digicore.devops.dtos.CreateAccountDTO;
import com.digicore.devops.dtos.DepositDTO;
import com.digicore.devops.dtos.ResponseDTO;
import com.digicore.devops.dtos.WithdrawalDTO;
import com.digicore.devops.models.Transaction;
import com.digicore.devops.services.AccountServices;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@ResponseStatus(HttpStatus.OK)
@RequestMapping("api/digicore/v1")
@CrossOrigin(maxAge = 3600, origins = "*")
public class CustomerController {
	@Autowired
	private AccountServices accountServices;
	
	@GetMapping("/account-info/{accountNumber}")
	public ResponseDTO<AccountQueryDTO> queryAccount(@PathVariable String accountNumber){
		log.info("--->>> Querying account number {} started", accountNumber);
		
		return accountServices.queryAccount(accountNumber);
	}
	
	@GetMapping("/account-statement/{accountNumber}")
	public ResponseDTO<List<Transaction>> getAccountStatement(@PathVariable String accountNumber){
		return accountServices.getAccountStatement(accountNumber);
	}
	
	@PostMapping("/deposit")
	public ResponseDTO<String> deposit (@Valid @RequestBody DepositDTO depositDTO){
		return accountServices.deposit(depositDTO);
	}
	
	@PostMapping("/withdraw")
	public ResponseDTO<String> withdraw (@Valid @RequestBody WithdrawalDTO withdrawalDTO){
		return accountServices.withdraw(withdrawalDTO);
	}
	
	@PostMapping("/create-account")
	public ResponseDTO<String> createAccount (@Valid @RequestBody CreateAccountDTO createAccountDTO){
		return accountServices.createAccount(createAccountDTO);
	}	
}