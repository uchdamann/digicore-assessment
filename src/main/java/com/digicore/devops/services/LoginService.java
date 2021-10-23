package com.digicore.devops.services;

import com.digicore.devops.dtos.ResponseDTO;

public interface LoginService {
	public ResponseDTO<String> login(String accountNumber, String accountPassword);
}
