package com.example.demo.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "ventas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CVentas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate fecha;
	
	@Column(nullable = false)
	private Integer cantidad;
	
	@Column(precision = 10, scale = 2)
	private BigDecimal total;
	
	@ManyToOne
	@JoinColumn(name = "id_articulo",nullable = false , foreignKey = @ForeignKey(foreignKeyDefinition = 
			"foreign key (id_articulo) references articulos (id)"))
	private CArticulo articulo;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente", nullable = false , foreignKey = @ForeignKey(foreignKeyDefinition = 
	"foreign key (id_cliente) references clientes(id) "))
	private CCliente cliente;
}
