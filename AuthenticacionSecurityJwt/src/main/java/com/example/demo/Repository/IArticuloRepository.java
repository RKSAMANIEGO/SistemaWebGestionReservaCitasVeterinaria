package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.CArticulo;

public interface IArticuloRepository extends JpaRepository<CArticulo, Long>{
	
	Optional<CArticulo> findByNombre(String nombre);
	
}
