package com.example.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Model.CServicios;

public interface IServiciosRepository extends JpaRepository<CServicios, Long>{
	
	
	static final String sqlBuscarByIdTipo="CALL buscarServicioByTipo(:tip);";
	@Query(value = sqlBuscarByIdTipo,nativeQuery = true)
	List<CServicios> buscarByTipoServ(@Param("tip") Long id);
	
	Optional<CServicios> findByNombre(String nombre); 

}
