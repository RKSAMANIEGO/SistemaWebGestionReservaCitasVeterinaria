package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.CTipoServicio;

public interface ITipoServicioRepository extends JpaRepository<CTipoServicio, Long> {

	Optional<CTipoServicio> findByNombre(String nombre);
	
}
