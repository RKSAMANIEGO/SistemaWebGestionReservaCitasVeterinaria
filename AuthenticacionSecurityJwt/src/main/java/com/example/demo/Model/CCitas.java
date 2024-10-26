package com.example.demo.Model;

import java.time.LocalDate;
import java.time.LocalTime;

import com.example.demo.Model.Enum.EnumEstado;
import com.example.demo.Model.Enum.EnumTipoAtencion;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "citas")
@Getter
@Setter
@AllArgsConstructor 
@NoArgsConstructor
@Builder
public class CCitas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private LocalDate fechaCita;
	
	@Column(nullable=false)
	private LocalTime horaCita;
	
	@Column(nullable=false)
	private String motivo;
	
	@Enumerated(EnumType.STRING)
	private EnumTipoAtencion tipo;
	
	private String ubicacion;
	
	@Enumerated(EnumType.STRING)
	private EnumEstado estado;
	
	@ManyToOne
	@JoinColumn(name = "id_mascota", nullable = false , foreignKey = @ForeignKey(foreignKeyDefinition = 
	"foreign key (id_mascota) references mascotas(id) "))
	private CMascota mascota;
	
	@ManyToOne
	@JoinColumn(name = "id_veterinario", nullable = false , foreignKey = @ForeignKey(foreignKeyDefinition =
	"foreign key (id_veterinario) references veterinario(id) "))
	private CVeterinario veterinario;
	
	@ManyToOne
	@JoinColumn(name = "id_servicio", nullable = false , foreignKey = @ForeignKey(foreignKeyDefinition = 
	"foreign key (id_servicio) references servicios(id) "))
	private CServicios servicio;
}
