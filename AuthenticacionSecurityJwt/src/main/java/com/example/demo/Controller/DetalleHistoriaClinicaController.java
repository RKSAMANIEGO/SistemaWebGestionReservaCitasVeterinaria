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

import com.example.demo.Model.CDetalleHistoriaClinica;
import com.example.demo.Service.IDetalleHcService;

@RestController
@RequestMapping("/detalleHistoriaClinica")
public class DetalleHistoriaClinicaController {
	
	@Autowired
	private IDetalleHcService detalleHcService;

	@GetMapping("/listar")
	public ResponseEntity<List<CDetalleHistoriaClinica>> listarDetalleHc(){
		return new ResponseEntity<List<CDetalleHistoriaClinica>>(detalleHcService.listarDetalleHC(), HttpStatus.OK);
	}
	
	@PostMapping("/guardar")
	public ResponseEntity<CDetalleHistoriaClinica> guardarDetalleHc(@RequestBody CDetalleHistoriaClinica detalleHc){
		return new ResponseEntity<CDetalleHistoriaClinica>(detalleHcService.guardarDetalleHc(detalleHc), HttpStatus.CREATED);
	}
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<CDetalleHistoriaClinica> actualizarDetalleHc(@RequestBody CDetalleHistoriaClinica detalleHc,@PathVariable Long id){
		return new ResponseEntity<CDetalleHistoriaClinica>(detalleHcService.actualizarDetalleHc(detalleHc, id), HttpStatus.OK);
	}
	
}
