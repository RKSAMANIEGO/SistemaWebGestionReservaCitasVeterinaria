package com.example.demo.Model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "historias_clinicas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CHistoriasClinicas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate fecha;
	
	private String observaciones;
	
	@OneToOne
	@JoinColumn(name = "id_mascota",nullable = false,unique = true ,foreignKey = @ForeignKey(foreignKeyDefinition = 
	"foreign key(id_mascota) references mascotas(id) "))
	private CMascota mascota;
	
}
