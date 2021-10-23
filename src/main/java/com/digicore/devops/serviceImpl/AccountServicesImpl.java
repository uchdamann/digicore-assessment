package com.digicore.devops.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digicore.devops.config.ConfigProcessor;
import com.digicore.devops.dtos.AccountQueryDTO;
import com.digicore.devops.dtos.ResponseDTO;
import com.digicore.devops.exceptions.AccountDetailsException;
import com.digicore.devops.exceptions.FinancialRestrictionException;
import com.digicore.devops.exceptions.InvalidAccountException;

import static com.digicore.devops.enums.ResponseMessages.*;

import java.math.BigDecimal;
import java.util.List;

import com.digicore.devops.models.AccountDetails;
import com.digicore.devops.models.AccountStub;
import com.digicore.devops.models.Customer;
import com.digicore.devops.models.Transaction;
import com.digicore.devops.services.AccountServices;
import com.digicore.devops.utilities.AccountUtil;

@Service
public class AccountServicesImpl implements AccountServices {

	@Autowired
	private AccountUtil accountUtil;
	@Autowired
	private ConfigProcessor prop;

	public void checkAcctNoLength(String accountNumber) {
		if (accountNumber.length() != prop.getAccountNumberLength())
			throw new InvalidAccountException("Account number must be "
		+ prop.getAccountNumberLength()+" digits");
	}
	
	public void checkDepositAmount(BigDecimal amount) {
		if (amount.compareTo(prop.getMaxDepositAmount()) == 1 || amount.compareTo(prop.getMinDepositAmount()) == 0) {
			throw new FinancialRestrictionException("Please type in a deposit between "
		+prop.getMinDepositAmount()+" and "+prop.getMaxDepositAmount());
		}
	}
	
	@Override
	public ResponseDTO<AccountQueryDTO> queryAccount(String accountNumber) {
		checkAcctNoLength(accountNumber);
		
		AccountQueryDTO accountQueryDTO = null;
		Customer[] customers = AccountStub.getCustomers();
		Customer customer = null;
		for (Customer c : customers) {
			AccountDetails ad = c.getAccountDetails();
			if (ad.getAccountNumber().equals(accountNumber)) {
				customer = c;
				break;
			}
		}

		if (customer == null) {
			throw new AccountDetailsException();
		}

		accountQueryDTO = accountUtil.generateAccountQueryDTO(customer);
		return ResponseDTO.newInstance(SUCCESS.getCode(), SUCCESS.getMessage(), accountQueryDTO, SUCCESS.getSuccess());
	}

	@Override
	public ResponseDTO<List<Transaction>> getAccountStatement(String accountNumber) {
		checkAcctNoLength(accountNumber);
		List<Transaction> transactions = null;
		Customer[] customers = AccountStub.getCustomers();
		Customer customer = null;
		for (Customer c : customers) {
			AccountDetails ad = c.getAccountDetails();
			if (ad.getAccountNumber().equals(accountNumber)) {
				customer = c;
				break;
			}
		}
		
		if (customer == null) {
			throw new AccountDetailsException();
		}
		
		transactions = customer.getAccountDetails().getTransactions();
		return ResponseDTO.newInstance(SUCCESS.getCode(), SUCCESS.getMessage(), transactions, SUCCESS.getSuccess());
	}

	@Override
	public ResponseDTO<String> makeDeposit(String accountNumber, BigDecimal amount) {
		checkAcctNoLength(accountNumber);
		checkDepositAmount(amount);
		BigDecimal balance;
		Customer[] customers = AccountStub.getCustomers();
		Customer customer = null;
		for (Customer c : customers) {
			AccountDetails ad = c.getAccountDetails();
			if (ad.getAccountNumber().equals(accountNumber)) {
				customer = c;
				break;
			}
		}
		
		if (customer == null) {
			throw new AccountDetailsException();
		}
		balance = customer.getAccountDetails().getBalance();
		balance.add(amount);
		String message = "Your deposit of "+amount+" was successful and your new balance is "+balance;
		return ResponseDTO.newInstance(SUCCESS.getCode(), SUCCESS.getMessage(), message, SUCCESS.getSuccess());
	}
}
