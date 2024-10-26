package com.example.demo.Model;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.Builder.Default;

@Entity
@Table(name = "veterinario")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CVeterinario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 100, nullable = false)
	private String nombres;
	
	@Column(length = 20, nullable =false, unique = true)
	private String documento;
	
	@Column(length = 100)
	private String especialidad;
	
	@Column(length = 15)
	private String telefono;  
	
	@Email
	@Column(length = 100, nullable = false , unique = true)
	private String email;	
	
	@Column(name = "fecha_registro")
	private LocalDate fechaRegistro;
	
	@Column(length = 255)
	private String imagen;

	@Default
	@Column(nullable = false)
	private Boolean estado=true;
	

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_usuario",nullable = false,foreignKey = @ForeignKey(foreignKeyDefinition = 
	"foreign key (id_usuario) references usuarios(id) "))
	private CUser usuario;
	
}
