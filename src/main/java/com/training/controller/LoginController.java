package com.training.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.training.exceptions.ClientErrorException;
import com.training.vo.LoginVO;

@RestController
@RequestMapping("/api")
@Validated
public class LoginController {
	
	@PostMapping("/login")
    public ResponseEntity<String> checkValidUser(@Valid @RequestBody LoginVO vo) {
    	System.out.println("checkValidUser begin");
    	if(vo.getUserName().equals("admin") && vo.getPassword().equals("password")) {
    		return new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
    	} else {
    		throw new ClientErrorException("User Name and Password is not valid");
    	}

	 	
	}

}
