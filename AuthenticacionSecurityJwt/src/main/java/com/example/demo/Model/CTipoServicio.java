package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.Builder.Default;

@Entity
@Table(name = "tipo_servicio")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CTipoServicio {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false , unique = true)
	private String nombre;
	
	@Default
	@Column(nullable = false)
	private Boolean estado=true;
}
