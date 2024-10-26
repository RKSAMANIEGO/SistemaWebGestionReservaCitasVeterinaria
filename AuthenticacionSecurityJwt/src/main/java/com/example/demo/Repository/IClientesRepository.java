package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.CCliente;

@Repository
public interface IClientesRepository extends JpaRepository<CCliente, Long> {
 
	static final String sqlQuery="CALL buscarCliente(:nom)";
	@Query(value = sqlQuery , nativeQuery = true)
	List<CCliente> findByNombres(@Param("nom") String nom);
	
	CCliente findByNombre(String nombre);
	
	Optional<CCliente> findByDocumento(String doc);
	Optional<CCliente> findByEmail(String email);
}
