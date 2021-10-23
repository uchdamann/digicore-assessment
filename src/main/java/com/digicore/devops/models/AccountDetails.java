package com.digicore.devops.models;

import java.math.BigDecimal;
import java.util.List;

import com.digicore.devops.enums.AccountType;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccountDetails {
	
	private String accountNumber;
	private BigDecimal balance;
	private AccountType accountType;
	private String password;
	private List<Transaction> transactions;
	
	public static AccountDetailsBuilder builder() {
		return new AccountDetailsBuilder();
	}
}
