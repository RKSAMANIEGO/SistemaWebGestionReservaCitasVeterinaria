package com.example.demo.Dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record AuthCreateUserRequest (@NotBlank String name,
							  @NotBlank String lastname,
							  @NotBlank String email,
							  @NotBlank String username,
							  @NotBlank String password,
							  @Valid AuthCreateRoleRequest roleRequest) {
	
	
	
	
	

}
