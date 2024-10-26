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

import com.example.demo.Model.CArticulo;
import com.example.demo.Service.IArticulosService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/articulo")
public class ArticuloController {

	@Autowired
	private IArticulosService aS;
	
	@GetMapping("/listarArticulos")
	public ResponseEntity<List<CArticulo>> listarArticulo(){
		return new ResponseEntity<List<CArticulo>>(aS.listarArticulo(), HttpStatus.OK);
	}
	
	@PostMapping("/guardarArticulo")
	public ResponseEntity<String> guardarArticulo(@Valid @RequestBody CArticulo a){
		if(aS.guardarArticulo(a)!=null) {
			aS.guardarArticulo(a);
			return new ResponseEntity<String>("Articulo "+a.getNombre()+" Guardado", HttpStatus.CREATED);
		}
		return new ResponseEntity<String>("El nombre del Articulo ya Existe¡..", HttpStatus.BAD_REQUEST);
	}
	
	@PutMapping("/actualizarArticulo/{id}")
	public ResponseEntity<CArticulo> actualizarArticulo( @RequestBody CArticulo a ,@PathVariable Long id){
		return new ResponseEntity<CArticulo>(aS.actualizarArticulo(a, id), HttpStatus.OK);
	}
	
	@PatchMapping("/desactivarArticulo/{id}")
	public ResponseEntity<String> desactivarArticulo(@PathVariable Long id){
		return new ResponseEntity<String>(aS.desactivar(id), HttpStatus.OK);
	}
	
	
}
