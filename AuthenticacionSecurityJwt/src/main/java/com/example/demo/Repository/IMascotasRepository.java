package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.Model.CMascota;

public interface IMascotasRepository extends JpaRepository<CMascota,Long> {

	static final String sqlList="select * from mascotas where estado=1;";
	@Query(value = sqlList, nativeQuery = true)
	List<CMascota> listarMascotasActivas(); 
	
	
	static final String mascotaInac="select * from mascotas where estado=0;";
	@Query(value = mascotaInac , nativeQuery = true)
	List<CMascota> listarMascotasInactivas();
	
	
	static final String buscarAnimal="CALL buscarAnimales(:stated,:valor);";
	@Query(value = buscarAnimal , nativeQuery = true)
	List<CMascota> buscarMascotaByNombreRazaSexo(@Param("stated") Integer stated ,@Param("valor") String valor);

	
	static final String sqlBuscarMascotaByNombreCliente="CALL buscarMascotaByNombreClientes (:stated , :cliente);";
	@Query(value = sqlBuscarMascotaByNombreCliente , nativeQuery = true)
	List<CMascota> buscarMascotaByNombreCliente(@Param("stated") Integer stated, @Param("cliente") String nombreCliente);
	
	
}
