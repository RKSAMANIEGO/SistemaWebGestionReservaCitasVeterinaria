package com.example.demo.Model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "compras")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CCompras {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Integer cantidad;
	
	private LocalDate fecha; 
	
	@Column(precision = 10, scale = 2, nullable=false)
	private BigDecimal precio;
	
	@Column(precision = 10, scale = 2)
	private BigDecimal total;
	
	@Column(nullable = false)
	private String proveedor;
	@ManyToOne
	@JoinColumn(name = "id_articulo", nullable = false, foreignKey = @ForeignKey(foreignKeyDefinition = 
	"foreign key (id_articulo) references articulos (id)"))
	private CArticulo articulo;
	

}
