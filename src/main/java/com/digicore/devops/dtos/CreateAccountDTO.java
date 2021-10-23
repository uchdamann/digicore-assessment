package com.digicore.devops.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import lombok.Data;

@Data
public class CreateAccountDTO {
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String accountName;
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String accountPassword;
	@NotNull
	@Min(value = 500)
	private Double initialDeposit;

}
