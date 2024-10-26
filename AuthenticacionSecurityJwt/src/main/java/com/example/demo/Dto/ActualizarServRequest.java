package com.example.demo.Dto;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public record ActualizarServRequest(
		Long id,
		String nombre,
		String descripcion,
		Integer duracion,
		BigDecimal costo,
		String imagen
	
		) {

}
