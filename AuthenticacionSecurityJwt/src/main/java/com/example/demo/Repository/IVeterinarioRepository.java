package com.example.demo.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.Model.CVeterinario;

public interface IVeterinarioRepository extends JpaRepository<CVeterinario, Long>{

	
	Optional<CVeterinario> findByDocumento(String documento);
	Optional<CVeterinario> findByEmail(String email);
}
