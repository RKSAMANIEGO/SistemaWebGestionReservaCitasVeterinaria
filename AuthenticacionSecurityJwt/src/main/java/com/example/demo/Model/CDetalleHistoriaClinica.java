package com.example.demo.Model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "detalle_historia_clinica")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CDetalleHistoriaClinica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate fecha;
	
	private String motivo;
	
	private String diagnostico;
	
	private String tratamiento;
	
	private String observaciones;
	
	@ManyToOne
	@JoinColumn(name = "id_historia_clinica",nullable = false ,foreignKey = @ForeignKey(foreignKeyDefinition = 
	"foreign key(id_historia_clinica) references historias_clinicas(id) "))
	private CHistoriasClinicas historiaClinica;
	
}
