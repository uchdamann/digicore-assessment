package com.digicore.devops.serviceImpl;

import static com.digicore.devops.enums.ResponseMessages.SUCCESS;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.digicore.devops.dtos.ResponseDTO;
import com.digicore.devops.exceptions.PasswordMismatchException;
import com.digicore.devops.exceptions.TokenNotFoundException;
import com.digicore.devops.models.AccountDetails;
import com.digicore.devops.models.Customer;
import com.digicore.devops.security.JwtTokenUtil;
import com.digicore.devops.security.JwtUser;
import com.digicore.devops.services.LoginService;
import com.digicore.devops.utilities.AccountUtil;

@Service
public class LoginServiceImpl implements LoginService {

	@Autowired
	private AccountUtil accountUtil;
	@Autowired
	private BCryptPasswordEncoder encoder;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Override
	public ResponseDTO<String> login(String accountNumber, String accountPassword) {
		Customer customer = accountUtil.validateAccount(accountNumber);
		AccountDetails accountDetails = customer.getAccountDetails();
		
		if (!encoder.matches(accountPassword, accountDetails.getPassword())) {
			throw new PasswordMismatchException();
		}
		
		JwtUser userDetails = JwtUser.builder().customer(customer).build();
		String token = jwtTokenUtil.generateToken(userDetails);
		if (token == null || token.isEmpty()) {
			throw new TokenNotFoundException();
		}
		
		return ResponseDTO.newInstance(SUCCESS.getCode(), SUCCESS.getMessage(), 
				token, SUCCESS.getSuccess());
	}


}
