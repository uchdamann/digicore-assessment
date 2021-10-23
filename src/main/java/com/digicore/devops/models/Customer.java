package com.digicore.devops.models;

import com.digicore.devops.enums.Roles;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Customer extends CommonFields{
	private BasicInfo basicInfo;
	private ContactInfo contactInfo;
	private AccountDetails accountDetails;
	private Roles role;
	
	public static CustomerBuilder builder() {
		return new CustomerBuilder();
	}

}
