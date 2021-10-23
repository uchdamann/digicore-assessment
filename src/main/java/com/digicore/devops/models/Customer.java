package com.digicore.devops.models;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Customer extends CommonFields{
	private BasicInfo basicInfo;
	private ContactInfo contactInfo;
	private AccountDetails accountDetails;
	
	public static CustomerBuilder builder() {
		return new CustomerBuilder();
	}

}
