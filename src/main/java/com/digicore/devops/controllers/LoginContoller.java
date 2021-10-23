package com.digicore.devops.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.digicore.devops.dtos.LoginDTO;
import com.digicore.devops.dtos.ResponseDTO;
import com.digicore.devops.services.LoginService;

@RestController
@ResponseStatus(HttpStatus.OK)
@RequestMapping("/api/digicore/login")
@CrossOrigin(maxAge = 3600, origins = "*")
public class LoginContoller {
	@Autowired
	private LoginService loginService;
	
	@PostMapping("/user")
	public ResponseDTO<String> login (@Valid @RequestBody LoginDTO loginDTO) {
		return loginService.login(loginDTO.getAccountNumber(), loginDTO.getAccountPassword());
	}
}
