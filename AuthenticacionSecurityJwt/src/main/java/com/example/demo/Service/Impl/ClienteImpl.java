package com.example.demo.Service.Impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.CreateClienteDto;
import com.example.demo.Model.CCliente;
import com.example.demo.Model.CUser;
import com.example.demo.Repository.IClientesRepository;
import com.example.demo.Repository.IUserRepository;
import com.example.demo.Service.IClienteService;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ClienteImpl implements IClienteService {

	@Autowired
	private IClientesRepository clientesRepository;
	
	@Autowired
	private IUserRepository iUserRepository;
	
	
	//1.-LISTAR TODOS LOS CLIENTES
	@Override
	public List<CCliente> listAllClientes() {
		return clientesRepository.findAll();
	}

	//2.-LISTAR LOS CLIENTES POR EL ID
	@Override
	public CCliente listarClienteById(Long id) {
		CCliente cE= clientesRepository.findById(id).get();
		return cE;
	}
	
	//3.-BUSCAR CLIENTE POR EL ID
	@Override
	public Optional<CCliente> findById(Long id) {
		return clientesRepository.findById(id);
	}
	//4.-BUSCAR CLIENTE POR EL NOMBRE EN ESPECIFICO
	@Override
	public  CCliente buscarPorNombre(String nombre) {
		return clientesRepository.findByNombre(nombre);
	}
	//5.-BUSCAR CLIENTE POR COINCIDENCIA DE NOMBRE
	@Override
	public List<CCliente> buscarPorNombres(String nom) {
		return clientesRepository.findByNombres(nom);
	}

	//6.-GUARDAR CLIENTES 
	@Override
	public CCliente guardarCliente(CreateClienteDto c, String username) {
		
				CUser uLogeado= iUserRepository.findByUsername(username)
						.orElseThrow( ()-> new EntityNotFoundException("Username no Encontrado"));
		
	if(!clientesRepository.findByDocumento(c.documento()).isPresent() && !clientesRepository.findByEmail(c.email()).isPresent() ) {
		CCliente cN=CCliente.builder()
				.nombre(c.nombre())
				.documento(c.documento())
				.email(c.email())
				.telefono(c.telefono())
				.direccion(c.direccion())
				.fechaRegistro(LocalDate.now())
				.imagen(c.imagen())
				.usuario(uLogeado)
				.build();
		return clientesRepository.save(cN); 
	}
			
	return null;
	}
	//7.-ACTUALIZAR CLIENTE POR EL ID EN EL ENDPOINT
	@Override
	public CCliente actualizarCliente(CCliente c, Long id) {
		    CCliente cBuscado = clientesRepository.findById(id)
		            .orElseThrow(() -> {
		            	String message = "Cliente no encontrado con ID: " + id;
		            	log.error(message);
		            	return new EntityNotFoundException(message);
		            });

		    cBuscado.setNombre(c.getNombre() != null ? c.getNombre() : cBuscado.getNombre());
		    cBuscado.setEmail(c.getEmail() != null ? c.getEmail() : cBuscado.getEmail());
		    cBuscado.setTelefono(c.getTelefono() != null ? c.getTelefono() : cBuscado.getTelefono());
		    cBuscado.setDireccion(c.getDireccion() != null ? c.getDireccion() : cBuscado.getDireccion());
		    cBuscado.setImagen(c.getImagen() != null ? c.getImagen() : cBuscado.getImagen());
	
		    return clientesRepository.save(cBuscado);
		}

	//8.-ELIMINAR CLIENTE
	@Override
	public Boolean eliminarCliente(Long id) {
		if(clientesRepository.findById(id).isPresent()) {
			clientesRepository.deleteById(id); 
			return true;
		}
		else {
			return false;
		}
	}


}
