package com.digicore.devops.utilities;

import org.springframework.stereotype.Component;

import com.digicore.devops.dtos.AccountQueryDTO;
import com.digicore.devops.models.AccountDetails;
import com.digicore.devops.models.BasicInfo;
import com.digicore.devops.models.Customer;

@Component
public class AccountUtil {
	
//	TODO: TBT
	public AccountQueryDTO generateAccountQueryDTO(Customer customer) {
		BasicInfo basicInfo = customer.getBasicInfo();
		AccountDetails accountDetails = customer.getAccountDetails();
		String accountName = basicInfo.getFirstName()+" "+basicInfo.getLastName();
		
		return AccountQueryDTO.newInstance(accountName, accountDetails.getAccountNumber(),
				accountDetails.getBalance().doubleValue());
	}

}
