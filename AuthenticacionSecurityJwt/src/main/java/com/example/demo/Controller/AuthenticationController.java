package com.example.demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.AuthCreateUserRequest;
import com.example.demo.Dto.AuthLoginRequest;
import com.example.demo.Dto.AuthResponse;
import com.example.demo.Service.UserDetailsServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {
	
	@Autowired
	private UserDetailsServiceImpl detailsServiceImpl;
	
	
	
	@PostMapping("/sign-up")
	public ResponseEntity<AuthResponse> register(@RequestBody @Valid AuthCreateUserRequest authCreateUser){
		return new ResponseEntity<>(this.detailsServiceImpl.createUser(authCreateUser), HttpStatus.CREATED);
	}
	
	
	@PostMapping("/log-in")
	public ResponseEntity<AuthResponse> login(@RequestBody @Valid AuthLoginRequest userRequest){
		return new ResponseEntity<>(this.detailsServiceImpl.loginUser(userRequest),HttpStatus.OK);
	}
	
	

}
