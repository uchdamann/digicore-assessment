package com.digicore.devops.dtos;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import lombok.Data;

@Data
@SuppressWarnings("deprecation")
public class DepositDTO {
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String accountNumber;
	@Min(value = 1)
	@Max(value = 1000000)
	private Double amount;
}
