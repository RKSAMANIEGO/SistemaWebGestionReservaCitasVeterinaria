package com.example.demo.Dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Builder;

@Builder
@JsonPropertyOrder({"id","fechaRegistro","cliente","articulo","cantidad","precio","total"})
public record VentaGuardarResponse(		
		 Long id,
		 LocalDate fechaRegistro,
		 BigDecimal precio,
		 Integer cantidad,
		 BigDecimal total,
		 String articulo,
		 String cliente) 

{

}
