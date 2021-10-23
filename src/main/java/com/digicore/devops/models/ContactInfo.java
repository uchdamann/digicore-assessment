package com.digicore.devops.models;

import com.digicore.devops.enums.Country;
import com.digicore.devops.enums.State;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ContactInfo extends CommonFields{
	private String phoneNumber;
	private String address;
	private State state;
	private Country country;
	
	public static ContactInfoBuilder builder() {
		return new ContactInfoBuilder();
	}

}
