package com.digicore.devops.services;

import java.util.List;

import com.digicore.devops.dtos.AccountQueryDTO;
import com.digicore.devops.dtos.CreateAccountDTO;
import com.digicore.devops.dtos.DepositDTO;
import com.digicore.devops.dtos.ResponseDTO;
import com.digicore.devops.dtos.WithdrawalDTO;
import com.digicore.devops.models.Transaction;

public interface AccountServices {
	public ResponseDTO<AccountQueryDTO> queryAccount(String accountNumber);
	public ResponseDTO<List<Transaction>> getAccountStatement(String accountNumber);
	public ResponseDTO<String> deposit(DepositDTO depositDTO);
	public ResponseDTO<String> withdraw(WithdrawalDTO withdrawalDTO);
	public ResponseDTO<String> createAccount(CreateAccountDTO createAccountDTO);
}
