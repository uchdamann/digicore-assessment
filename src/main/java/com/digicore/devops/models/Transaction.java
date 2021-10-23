package com.digicore.devops.models;

import java.math.BigDecimal;
import java.util.Date;

import com.digicore.devops.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonIgnoreProperties({"createdon", "updatedon", "isactive"})
public class Transaction extends CommonFields {
	private Date transactionDate;
	private TransactionType transactionType;
	private String narration;
	private BigDecimal amount;
	private BigDecimal accountBalance;
	
	public static TransactionBuilder builder() {
		return new TransactionBuilder();
	}

}
