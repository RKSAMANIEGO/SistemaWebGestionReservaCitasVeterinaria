package com.example.demo.Model;

import java.math.BigDecimal;

import com.example.demo.Model.Enum.EnumTipo;

import jakarta.persistence.*;
import lombok.*;
import lombok.Builder.Default;

@Entity
@Table(name = "articulos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CArticulo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 100, nullable = false, unique=true )
	private String nombre;
	
	private String descripcion;
	
	@Enumerated(EnumType.STRING)
	private EnumTipo tipo;
	
	@Column(precision = 10 ,scale = 2)
	private BigDecimal  precio;
	
	private Integer stock;
	
	@Column(length = 255 )
	private String imagen;
	
	@Default
	@Column(nullable=false)
	private boolean estado=true;

}
