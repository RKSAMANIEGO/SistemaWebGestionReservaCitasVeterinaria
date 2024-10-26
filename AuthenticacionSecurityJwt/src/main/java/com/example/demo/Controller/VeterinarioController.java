package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.CVeterinario;
import com.example.demo.Service.IVeterinarioService;

@RestController
@RequestMapping("/veterinario")
public class VeterinarioController {

	@Autowired
	private IVeterinarioService veterinarioService;
	
	@GetMapping("/listar")
	public ResponseEntity<List<CVeterinario>> listarVeterinario(){
		return new ResponseEntity<List<CVeterinario>>(veterinarioService.listarVeterinario() , HttpStatus.OK);
	}
	
	@PostMapping("/guardar")
	public ResponseEntity<String> guardarVeterinario(@RequestBody CVeterinario v){
		if(veterinarioService.guardarVeterinario(v)==null) {
			return new ResponseEntity<>("El documento o el correo ya existen", HttpStatus.BAD_REQUEST);
		}
		veterinarioService.guardarVeterinario(v);
		return new ResponseEntity<>("Veterinario Guardado", HttpStatus.CREATED);
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<CVeterinario> actualizarVeterinario(@RequestBody CVeterinario v,@PathVariable Long id){
		return new ResponseEntity<CVeterinario>(veterinarioService.actualizarVeterinario(v, id), HttpStatus.OK);
	}
	
	@PatchMapping("/desactivar/{id}")
	public ResponseEntity<String> desactivarVeterinario(@PathVariable Long id){
		veterinarioService.desactivarVeterinario(id);
		return new ResponseEntity<String>("Veterinario Desactivado.", HttpStatus.OK);
	}
	
}
