package com.digicore.devops.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digicore.devops.config.ConfigProcessor;
import com.digicore.devops.dtos.AccountQueryDTO;
import com.digicore.devops.dtos.CreateAccountDTO;
import com.digicore.devops.dtos.DepositDTO;
import com.digicore.devops.dtos.ResponseDTO;
import com.digicore.devops.dtos.WithdrawalDTO;
import com.digicore.devops.exceptions.FinancialRestrictionException;
import com.digicore.devops.exceptions.PasswordMismatchException;

import static com.digicore.devops.enums.ResponseMessages.*;

import java.math.BigDecimal;
import java.util.List;

import com.digicore.devops.models.AccountDetails;
import com.digicore.devops.models.Transaction;
import com.digicore.devops.services.AccountServices;
import com.digicore.devops.utilities.AccountUtil;

@Service
public class AccountServicesImpl implements AccountServices {

	@Autowired
	private AccountUtil accountUtil;
	
	@Autowired
	private ConfigProcessor prop;

	@Override
	public ResponseDTO<AccountQueryDTO> queryAccount(String accountNumber) {
		accountUtil.checkAcctNoLength(accountNumber);

		AccountQueryDTO accountQueryDTO = null;
		
		accountQueryDTO = accountUtil.generateAccountQueryDTO(
				accountUtil.validateAccount(accountNumber));
		return ResponseDTO.newInstance(SUCCESS.getCode(), SUCCESS.getMessage(), 
				accountQueryDTO, SUCCESS.getSuccess());
	}

	@Override
	public ResponseDTO<List<Transaction>> getAccountStatement(String accountNumber) {
		accountUtil.checkAcctNoLength(accountNumber);
		List<Transaction> transactions = null;
		
		transactions = accountUtil.validateAccount(accountNumber)
				.getAccountDetails().getTransactions();
		return ResponseDTO.newInstance(SUCCESS.getCode(), SUCCESS.getMessage(), 
				transactions, SUCCESS.getSuccess());
	}

	@Override
	public ResponseDTO<String> deposit(DepositDTO depositDTO) {
		BigDecimal depositAmount = new BigDecimal(depositDTO.getAmount());
		accountUtil.checkAcctNoLength(depositDTO.getAccountNumber());
		accountUtil.validateDepositAmount(depositAmount);
		
		BigDecimal balance = accountUtil.validateAccount(depositDTO.getAccountNumber())
				.getAccountDetails().getBalance();
		BigDecimal newBalance = balance.add(depositAmount);
		
		String message = "Your deposit of " + depositAmount 
				+ " was successful and your new balance is " + newBalance;
		return ResponseDTO.newInstance(SUCCESS.getCode(), SUCCESS.getMessage(), 
				message, SUCCESS.getSuccess());
	}

	@Override
	public ResponseDTO<String> withdraw(WithdrawalDTO withdrawalDTO) {
		BigDecimal withdrawnAmount = new BigDecimal(withdrawalDTO.getWithdrawnAmount());
		accountUtil.checkAcctNoLength(withdrawalDTO.getAccountNumber());
		accountUtil.validateAmount(withdrawnAmount);
		
		AccountDetails accountDetails = accountUtil.validateAccount(withdrawalDTO.getAccountNumber())
				.getAccountDetails();
		if (!accountDetails.getPassword().equals(withdrawalDTO.getAccountPassword())) {
			throw new PasswordMismatchException();
		}
		
		BigDecimal balance = accountDetails.getBalance();
		BigDecimal newBalance = balance.subtract(withdrawnAmount);
		
		if (newBalance.doubleValue() < prop.getMinApprovedBalance()) {
			throw new FinancialRestrictionException("Insufficient balance");
		}
		
		String message = "Your Withdrawal of " + withdrawnAmount 
				+ " was successful and your new balance is " + newBalance;
		return ResponseDTO.newInstance(SUCCESS.getCode(), SUCCESS.getMessage(), 
				message, SUCCESS.getSuccess());
	}

	@Override
	public ResponseDTO<String> createAccount(CreateAccountDTO createAccountDTO) {
		String accountNumber = null;
		
		do {
			accountNumber = accountUtil.generateAccountNumber();
		}
		while (!accountUtil.isUnique(accountNumber));
		
		if (createAccountDTO.getInitialDeposit() < prop.getMinApprovedBalance()) {
			throw new FinancialRestrictionException(MINIMUM_OPENING_BALANCE
					+ String.valueOf(prop.getMinApprovedBalance()));
		}
		
		accountUtil.createAccount(createAccountDTO, accountNumber);
		
		return ResponseDTO.newInstance(SUCCESS.getCode(), SUCCESS.getMessage(), 
				ACCOUNT_CREATED.getMessage(), SUCCESS.getSuccess());
	}
}
