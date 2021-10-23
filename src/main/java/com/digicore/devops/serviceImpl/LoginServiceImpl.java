package com.digicore.devops.serviceImpl;

import org.springframework.stereotype.Service;

import com.digicore.devops.dtos.ResponseDTO;
import com.digicore.devops.services.LoginService;

@Service
public class LoginServiceImpl implements LoginService {

	@Override
	public ResponseDTO<String> login(String accountNumber, String accountPassword) {
		
		return null;
	}


}
