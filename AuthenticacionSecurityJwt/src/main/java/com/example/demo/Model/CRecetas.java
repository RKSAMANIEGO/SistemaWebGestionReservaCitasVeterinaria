package com.example.demo.Model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "recetas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CRecetas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String medicamentos;
	
	private String descripcion;
	
	private LocalDate fecha;
	
	@ManyToOne
	@JoinColumn(name = "id_cita" , nullable = false , foreignKey = @ForeignKey(foreignKeyDefinition = 
	"foreign key (id_cita) references citas(id) "))
	private CCitas cita;
	
	
	@ManyToOne
	@JoinColumn(name = "id_detalle_historia_clinica", nullable = false , foreignKey = @ForeignKey(foreignKeyDefinition = 
	"foreign key (id_detalle_historia_clinica) references detalle_historia_clinica(id) "))
	private CDetalleHistoriaClinica detalleHistoriaClinica;
}
