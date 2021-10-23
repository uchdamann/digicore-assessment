package com.digicore.devops.dtos;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import lombok.Data;

@Data
@SuppressWarnings("deprecation")
public class WithdrawalDTO {
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String accountNumber;
	private String accountPassword;
	private Double withdrawnAmount;
}