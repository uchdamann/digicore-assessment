package com.digicore.devops.utilities;

import java.math.BigDecimal;
import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.digicore.devops.config.ConfigProcessor;
import com.digicore.devops.dtos.AccountQueryDTO;
import com.digicore.devops.exceptions.AccountDetailsException;
import com.digicore.devops.exceptions.FinancialRestrictionException;
import com.digicore.devops.exceptions.InvalidAccountException;
import com.digicore.devops.models.AccountDetails;
import com.digicore.devops.models.AccountStub;
import com.digicore.devops.models.BasicInfo;
import com.digicore.devops.models.Customer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AccountUtil {

	@Autowired
	private ConfigProcessor prop;

//	TODO: TBT
	public AccountQueryDTO generateAccountQueryDTO(Customer customer) {
		BasicInfo basicInfo = customer.getBasicInfo();
		AccountDetails accountDetails = customer.getAccountDetails();
		String accountName = basicInfo.getFirstName() + " " + basicInfo.getLastName();

		return AccountQueryDTO.newInstance(accountName, accountDetails.getAccountNumber(),
				accountDetails.getBalance().doubleValue());
	}

//	TODO: TBT
	public void checkAcctNoLength(String accountNumber) {
		if (accountNumber.length() != prop.getAccountNumberLength())
			throw new InvalidAccountException("Account number must be " + prop.getAccountNumberLength() + " digits");
	}

//	TODO: TBT
	public void validateDepositAmount(BigDecimal amount) {
		if (amount.doubleValue() > prop.getMaxDepositAmount()) {
			throw new FinancialRestrictionException("Deposit cannot be more than " + prop.getMaxDepositAmount());
		}
		
		validateAmount(amount);
	}
	
	public void validateAmount (BigDecimal amount) {
		if (amount.doubleValue() < prop.getMinAmount()) {
			throw new FinancialRestrictionException("Amount cannot be less than " + prop.getMinAmount());
		}
	}

//	TODO: TBT
	public String generateAccountNumber() {
		StringBuilder sb = new StringBuilder();
		SecureRandom random = new SecureRandom();
		char[] accountNumberArray = prop.getAccountCharacters().toCharArray();

		for (int count = 0; count < prop.getAccountNumberLength(); count++) {
			sb.append(accountNumberArray[random.nextInt(prop.getAccountNumberLength())]);
		}

		return sb.toString();
	}

//	TODO: TBT
	public Customer validateAccount(String accountNumber) {
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

		return customer;
	}
	
//	TODO: TBT
	public boolean isUnique (String accountNumber) {
		boolean isUnique = true;
		Customer[] customers = AccountStub.getCustomers();
		
		for (Customer c : customers) {
			AccountDetails ad = c.getAccountDetails();
			if (ad.getAccountNumber().equals(accountNumber)) {
				isUnique = false;
				break;
			}
		}
		
		return isUnique;		
	}
}
