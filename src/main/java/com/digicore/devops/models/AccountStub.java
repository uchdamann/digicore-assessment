package com.digicore.devops.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.digicore.devops.enums.TransactionType;

import static com.digicore.devops.enums.TransactionType.*;
import static com.digicore.devops.enums.Roles.*;

import lombok.Getter;

import static com.digicore.devops.enums.AccountType.*;

import static com.digicore.devops.enums.State.*;
import static com.digicore.devops.enums.Country.*;

@SuppressWarnings("unused")
public class AccountStub {
	private static Transaction[] transactions=new Transaction[4];
	private static BasicInfo[] basicInfos=new BasicInfo[5];
	private static ContactInfo[] contactInfos=new ContactInfo[5];
	private static AccountDetails[] accountDetails=new AccountDetails[5];
	@Getter
	private static Customer[] customers=new Customer[5];
	
	static {
		
		transactions[0]=Transaction.builder().accountBalance(new BigDecimal(10000))
				.transactionDate(new Date()).transactionType(DEPOSIT).amount(new BigDecimal(2000))
				.narration("Deposit").build();
		transactions[1]=Transaction.builder().accountBalance(new BigDecimal(15000))
				.transactionDate(new Date()).transactionType(WITHDRAWAL).amount(new BigDecimal(3000))
				.narration("Withdrawal").build();
		transactions[2]=Transaction.builder().accountBalance(new BigDecimal(20000))
				.transactionDate(new Date()).transactionType(DEPOSIT).amount(new BigDecimal(4000))
				.narration("Deposit").build();
		transactions[3]=Transaction.builder().accountBalance(new BigDecimal(25000))
				.transactionDate(new Date()).transactionType(WITHDRAWAL).amount(new BigDecimal(5000))
				.narration("Withdrawal").build();
		
		basicInfos[0]=BasicInfo.builder().age(42).firstName("Mike").lastName("Okiro").build();
		basicInfos[1]=BasicInfo.builder().age(32).firstName("Jack").lastName("Okeke").build();
		basicInfos[2]=BasicInfo.builder().age(46).firstName("Ekene").lastName("Okonkwo").build();
		basicInfos[3]=BasicInfo.builder().age(61).firstName("Ayomide").lastName("Olanike").build();
		basicInfos[4]=BasicInfo.builder().age(26).firstName("Aliyu").lastName("Danjuma").build();
		
		contactInfos[0]=ContactInfo.builder().address("No 8 Market Street, Egbeda")
				.country(NIGERIA).state(RIVERS).phoneNumber("08123456789").build();
		contactInfos[1]=ContactInfo.builder().address("No 9 Market Street, Egbeda")
				.country(NIGERIA).state(ENUGU).phoneNumber("08123456789").build();
		contactInfos[2]=ContactInfo.builder().address("No 23 Market Street, Egbeda")
				.country(NIGERIA).state(IMO).phoneNumber("08123456789").build();
		contactInfos[3]=ContactInfo.builder().address("No 85 Market Street, Egbeda")
				.country(NIGERIA).state(OGUN).phoneNumber("08123456789").build();
		contactInfos[4]=ContactInfo.builder().address("No 89 Market Street, Egbeda")
				.country(NIGERIA).state(LAGOS).phoneNumber("08123456789").build();
		
		accountDetails[0]=AccountDetails.builder().accountNumber("2314323453")
				.accountType(SAVINGS).balance(BigDecimal.ONE).password("1234")
				.transactions(Arrays.asList(transactions[0])).build();
		accountDetails[1]=AccountDetails.builder().accountNumber("3314323453")
				.accountType(CURRENT).balance(BigDecimal.ONE).password("1234")
				.transactions(Arrays.asList(transactions[1])).build();
		accountDetails[2]=AccountDetails.builder().accountNumber("4314323453")
				.accountType(ELITE).balance(BigDecimal.ONE).password("1234")
				.transactions(Arrays.asList(transactions[2])).build();
		accountDetails[3]=AccountDetails.builder().accountNumber("5314323453")
				.accountType(PREMIUM).balance(new BigDecimal(150000)).password("1234")
				.transactions(Arrays.asList(transactions[3])).build();
		accountDetails[4]=AccountDetails.builder().accountNumber("6314323453")
				.accountType(FIXED_DEPOSIT).balance(BigDecimal.ONE).password("1234")
				.transactions(Arrays.asList(transactions)).build();
		
		customers[0]=Customer.builder().accountDetails(accountDetails[0])
				.basicInfo(basicInfos[0]).contactInfo(contactInfos[0]).role(CUSTOMER).build();
		customers[1]=Customer.builder().accountDetails(accountDetails[1])
				.basicInfo(basicInfos[1]).contactInfo(contactInfos[1]).role(CUSTOMER).build();
		customers[2]=Customer.builder().accountDetails(accountDetails[2])
				.basicInfo(basicInfos[2]).contactInfo(contactInfos[2]).role(CUSTOMER).build();
		customers[3]=Customer.builder().accountDetails(accountDetails[3])
				.basicInfo(basicInfos[3]).contactInfo(contactInfos[3]).role(CUSTOMER).build();
		customers[4]=Customer.builder().accountDetails(accountDetails[4])
				.basicInfo(basicInfos[4]).contactInfo(contactInfos[4]).role(CUSTOMER).build();
				
	}
	
	public static AccountStub getInstance() {
		return new AccountStub();
	}
	
}
