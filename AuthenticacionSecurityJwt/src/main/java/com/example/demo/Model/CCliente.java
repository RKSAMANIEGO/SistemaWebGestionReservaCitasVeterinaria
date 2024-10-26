package com.example.demo.Model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "clientes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CCliente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 100, nullable = false)
	private String nombre;
	
	@Column(length = 20,nullable=false, unique = true)
	private String documento;
	
	@Column(length = 100 ,nullable = false ,unique = true)
	private String email;
	
	@Column(length = 15)
	private String telefono;
	
	@Column(length = 255)
	private String direccion;
	
	@Column(name = "fecha_registro")
	private LocalDate fechaRegistro;
	
	@Column(length = 255)
	private String imagen;
	
	@OneToOne
	@JoinColumn(name = "id_usuario",nullable = false,foreignKey = @ForeignKey(foreignKeyDefinition = 
	"foreign key (id_usuario) references usuarios(id) "))
	@JsonIgnore
	private CUser usuario;
	
}
