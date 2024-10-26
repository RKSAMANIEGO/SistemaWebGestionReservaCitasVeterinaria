package com.example.demo.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Model.CRol;

@Repository
public interface IRoleRepository extends CrudRepository<CRol, Long> {

	List<CRol> findCRolsByNameRoleIn(List<String> roleName);  // TENER ENCUENTA ESTE METODO ES DISTINTO 
	
}
