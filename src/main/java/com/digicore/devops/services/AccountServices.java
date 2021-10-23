package com.digicore.devops.services;

import java.math.BigDecimal;
import java.util.List;

import com.digicore.devops.dtos.AccountQueryDTO;
import com.digicore.devops.dtos.ResponseDTO;
import com.digicore.devops.models.Transaction;

public interface AccountServices {
	public ResponseDTO<AccountQueryDTO> queryAccount(String accountNumber);
	public ResponseDTO<List<Transaction>> getAccountStatement(String accountNumber);
	public ResponseDTO<String> makeDeposit(String accountNumber, BigDecimal amount);	
}
