package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Dto.CreateClienteDto;
import com.example.demo.Model.CCliente;
import com.example.demo.Service.IClienteService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/cliente")
@Slf4j
public class ClienteController {

	@Autowired
	private IClienteService clienteService;
	
	@GetMapping("/listarClientes")
	public ResponseEntity<List<CCliente>> listAllClientes(){
		return new ResponseEntity<List<CCliente>>(clienteService.listAllClientes(), HttpStatus.OK);
	}
	
	@GetMapping("/buscarNombres/{nombre}")
	public ResponseEntity<List<CCliente>> buscarPorNombres(@PathVariable String nombre){
		return new ResponseEntity<List<CCliente>>(clienteService.buscarPorNombres(nombre), HttpStatus.OK);
	}
	
	
	@GetMapping("/listarClienteFindById/{id}")
	public ResponseEntity<CCliente> listarClienteById(@PathVariable Long id){
		return new ResponseEntity<CCliente>(clienteService.listarClienteById(id),HttpStatus.OK);	
	}
	
	@PostMapping("/guardarCliente/{username}")
	public ResponseEntity<String> guardarCliente(@Valid @RequestBody CreateClienteDto c,@PathVariable String username){
			if(clienteService.guardarCliente(c, username)==null) {
				return new ResponseEntity<String>("El documento o Email ya se encuentran Registrado¡...", HttpStatus.BAD_REQUEST);
			}
		
		    clienteService.guardarCliente(c,username);
			return new ResponseEntity<String>("Cliente Registrado", HttpStatus.CREATED);
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<CCliente> actualizaCliente(@RequestBody CCliente cliente ,@PathVariable Long id){
		if(clienteService.findById(id).isPresent()) {
			CCliente cA= clienteService.actualizarCliente(cliente, id);
			return new ResponseEntity<CCliente> (cA,HttpStatus.OK);
		
		}
		
		return new ResponseEntity<> (HttpStatus.NOT_FOUND);
	}
	
	@DeleteMapping("/eliminarCliente/{id}")
	public ResponseEntity<String> eliminarCliente(@PathVariable Long id){
		if(clienteService.findById(id).isPresent()) {
			CCliente cE= clienteService.findById(id).get();
			clienteService.eliminarCliente(id);
			return new ResponseEntity<String>("Cliente "+cE.getNombre()+" Eliminado Correctamente",HttpStatus.OK);	
		}
			return new ResponseEntity<String>("Cliente No EXISTE",HttpStatus.NOT_FOUND);
	}
	

}
