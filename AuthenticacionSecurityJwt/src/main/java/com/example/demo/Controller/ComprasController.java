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

import com.example.demo.Model.CCompras;
import com.example.demo.Service.IComprasService;

@RestController
@RequestMapping("/compras")
public class ComprasController {

	@Autowired
	private IComprasService cService;

	@GetMapping("/listarCompras")
	public ResponseEntity<List<CCompras>> listarCompras(){
		return new ResponseEntity<List<CCompras>>(cService.listarCompras(),HttpStatus.OK);
	}
	
	@PostMapping("/guardarCompras")
	public ResponseEntity<String> guardarCompras(@RequestBody CCompras c){
		cService.guardarCompras(c);
		return new ResponseEntity<String>("Compra Guardada.",HttpStatus.CREATED);
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<CCompras> actualizarCompras(@RequestBody CCompras c,@PathVariable Long id){
		return new ResponseEntity<CCompras>(cService.actualizarCompras(c, id) , HttpStatus.OK);
	}
	
	@DeleteMapping("/eliminiarCompra/{id}")
	public ResponseEntity<String> eliminarCompra(@PathVariable Long id){
		return new ResponseEntity<String>(cService.eliminarCompras(id),HttpStatus.OK);
	}
	
	
}
