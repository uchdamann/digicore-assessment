package com.digicore.devops.controllers;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.digicore.devops.dtos.LoginDTO;
import com.digicore.devops.dtos.ResponseDTO;

@RestController
@ResponseStatus(HttpStatus.OK)
@RequestMapping("/api/digicore/login")
@CrossOrigin(maxAge = 3600, origins = "*")
public class LoginContoller {
	
	public ResponseDTO<String> login (@Valid @RequestBody LoginDTO loginDTO) {
		return null;
	}
}
