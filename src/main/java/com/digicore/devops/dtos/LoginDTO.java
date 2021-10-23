package com.digicore.devops.dtos;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.validator.constraints.SafeHtml.WhiteListType;

import lombok.Data;

@SuppressWarnings("deprecation")
@Data
public class LoginDTO {
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String accountNumber;
	@NotBlank
	@SafeHtml(whitelistType = WhiteListType.NONE)
	private String accountPassword;
	

}
