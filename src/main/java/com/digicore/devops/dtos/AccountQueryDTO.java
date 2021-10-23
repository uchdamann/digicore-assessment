package com.digicore.devops.dtos;

import lombok.Getter;

@Getter
public class AccountQueryDTO{
	private String accountName;
	private String accountNumber;
	private Double balance;
	
	private AccountQueryDTO(String accountName, String accountNumber, 
			Double balance) {
		this.accountName=accountName;
		this.accountNumber=accountNumber;
		this.balance=balance;
	}
	
	public static AccountQueryDTO newInstance(String accountName, String accountNumber, 
			Double balance) {
		return new AccountQueryDTO(accountName, accountNumber, balance);
	}
}
