package com.example.demo.Dto;

import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Validated
public record CreateClienteDto(
	@NotBlank(message = "El nombre no puede estar vacio") String nombre , 
	@NotBlank(message = "Documento no puede estar vacio") String documento,
	@NotBlank(message = "El correo no puede estar vacio") @Email(message = "Ingrese un correo valido") String email,
	 String telefono,
	 String direccion,
     String imagen) {

}
