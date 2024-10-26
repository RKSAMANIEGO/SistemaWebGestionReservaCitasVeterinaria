package com.example.demo.Service;

import java.util.List;
import java.util.Optional;

import com.example.demo.Dto.CreateClienteDto;
import com.example.demo.Model.CCliente;

public interface IClienteService {
	
	List<CCliente> listAllClientes();
	CCliente listarClienteById(Long id);
	CCliente guardarCliente(CreateClienteDto c, String username);
	CCliente actualizarCliente(CCliente c ,Long id);
	Boolean eliminarCliente(Long id);
	Optional<CCliente>  findById(Long id);
	CCliente buscarPorNombre(String nombre);
	List<CCliente> buscarPorNombres(String nom);
	

}
