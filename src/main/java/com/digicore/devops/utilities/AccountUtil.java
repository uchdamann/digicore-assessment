package com.digicore.devops.utilities;

import static com.digicore.devops.enums.Roles.CUSTOMER;

import java.math.BigDecimal;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.digicore.devops.config.ConfigProcessor;
import com.digicore.devops.dtos.AccountQueryDTO;
import com.digicore.devops.dtos.CreateAccountDTO;
import com.digicore.devops.enums.AccountType;
import com.digicore.devops.enums.TransactionType;
import com.digicore.devops.exceptions.AccountDetailsException;
import com.digicore.devops.exceptions.FinancialRestrictionException;
import com.digicore.devops.exceptions.InvalidAccountException;
import com.digicore.devops.models.AccountDetails;
import com.digicore.devops.models.AccountStub;
import com.digicore.devops.models.BasicInfo;
import com.digicore.devops.models.Customer;
import com.digicore.devops.models.Transaction;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class AccountUtil {

	@Autowired
	private ConfigProcessor prop;
	@Autowired
	private BCryptPasswordEncoder encoder;

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
	
//TODO: TBT
	public void createAccount (final CreateAccountDTO createAccountDTO, String accountNumber) {
		Transaction transaction = Transaction.builder().transactionDate(new Date())
				.transactionType(TransactionType.DEPOSIT)
				.amount(new BigDecimal(createAccountDTO.getInitialDeposit())).build();
		BasicInfo basicInfo = BasicInfo.builder().firstName(createAccountDTO.getAccountName())
				.lastName(createAccountDTO.getAccountName()).build();
		AccountDetails accountDetails = AccountDetails.builder().accountNumber(accountNumber)
				.accountType(AccountType.SAVINGS)
				.password(encoder.encode(createAccountDTO.getAccountPassword()))
				.transactions(Arrays.asList(transaction)).build();
				
		Customer.builder().accountDetails(accountDetails).basicInfo(basicInfo).role(CUSTOMER).build();		
	}
}
