package com.example.demo.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Model.CRecetas;
import com.example.demo.Service.IRecetasService;

@RestController
@RequestMapping("/recetas")
public class RecetasController {

	@Autowired
	private IRecetasService recetaS;
	
	@GetMapping("/listarRecetas")
	public ResponseEntity<List<CRecetas>> listarRecetas(){
		return new ResponseEntity<List<CRecetas>>(recetaS.listarRecetas(), HttpStatus.OK);
	}
	
	@PostMapping("/guardarRecetas")
	public ResponseEntity<CRecetas> guardarRecetas(@RequestBody CRecetas r){
		return new ResponseEntity<CRecetas>(recetaS.guardarRecetas(r),HttpStatus.CREATED);
	}
	
	@PutMapping("/actualizarRecetas/{id}")
	public ResponseEntity<CRecetas> actualizarRecetas(@RequestBody CRecetas r,@PathVariable Long id){
		return new ResponseEntity<CRecetas>(recetaS.actualizarRecetas(r, id), HttpStatus.OK);
	}
	
	
	
}
