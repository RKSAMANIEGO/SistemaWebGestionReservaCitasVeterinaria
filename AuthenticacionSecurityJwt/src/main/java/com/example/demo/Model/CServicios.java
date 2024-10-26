package com.example.demo.Model;

import java.math.BigDecimal;

import jakarta.persistence.*;
import lombok.*;
import lombok.Builder.Default;

@Entity
@Table(name = "servicios")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CServicios {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(length = 100, nullable = false , unique = true)
	private String nombre;
	
	private String descripcion;
	
	private Integer duracion;
	
	@Column(precision = 10 , scale = 2)
	private BigDecimal  costo;
	
	@Column(length = 255)
	private String imagen;

	@Default
	@Column(nullable = false)
	private Boolean estado=true;
	
	@ManyToOne
	@JoinColumn(name = "id_tipo_Servicio", nullable = false , foreignKey = @ForeignKey(foreignKeyDefinition =
	"foreign key (id_tipo_Servicio) references tipo_servicio(id) "))
	private CTipoServicio tipoServicio;
}
