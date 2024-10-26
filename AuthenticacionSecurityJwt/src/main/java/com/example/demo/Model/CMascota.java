package com.example.demo.Model;

import java.time.LocalDate;

import com.example.demo.Model.Enum.EnumSexo;

import jakarta.persistence.*;
import lombok.*;
import lombok.Builder.Default;

@Entity
@Table(name = "mascotas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CMascota {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(length = 100, nullable = false)
	private String nombre;
	
	@Column(length = 100, nullable = false)
	private String raza;
	
	private Integer edad;
	
	@Enumerated(EnumType.STRING)
	private EnumSexo sexo;
	
	@Column(name = "fecha_registro")
	private LocalDate fechaRegistro;
	
	@Column(length = 255)
	private String imagen;
	
	@Default
    @Column(nullable = false)
    private  Boolean estado= true; 
	
	@ManyToOne
	@JoinColumn(name = "id_cliente",nullable = false, foreignKey = @ForeignKey(foreignKeyDefinition = 
	"foreign key (id_cliente) references clientes(id) "))
	private CCliente cliente;
	
}
