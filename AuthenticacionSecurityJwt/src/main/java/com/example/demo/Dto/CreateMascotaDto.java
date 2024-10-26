package com.example.demo.Dto;

import com.example.demo.Model.Enum.EnumSexo;

import jakarta.validation.constraints.NotBlank;

public record CreateMascotaDto(
		
		@NotBlank(message = "El nombre de la mascota es obligatorio") String nombres,
		@NotBlank(message = "La raza de la mascota es obligatorio") String raza,
		Integer edad,
		EnumSexo sexo,
		String imagen	
		
		) {

	public CreateMascotaDto(String nombres,String raza,Integer edad,String sexo,String imagen){
		this(nombres,raza,edad,Enum.valueOf(EnumSexo.class, sexo.toUpperCase()),imagen);	
	}
}
