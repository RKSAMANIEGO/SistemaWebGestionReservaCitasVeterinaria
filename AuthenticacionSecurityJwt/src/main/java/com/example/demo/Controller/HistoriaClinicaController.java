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

import com.example.demo.Model.CHistoriasClinicas;
import com.example.demo.Service.IHistoriaClinicaService;

@RestController
@RequestMapping("/historiaClinica")
public class HistoriaClinicaController {

	@Autowired
	private IHistoriaClinicaService hcService;
	
	@GetMapping("/listarHistoriasClinicas")
	public ResponseEntity<List<CHistoriasClinicas>> listarHistoriasClinicas(){
		return new ResponseEntity<List<CHistoriasClinicas>>(hcService.listarHC(), HttpStatus.OK);
	}
	
	@PostMapping("/guardarHistoriasClinicas")
	public ResponseEntity<CHistoriasClinicas> guardarHistoriasClinicas(@RequestBody CHistoriasClinicas historiaC){
		return new ResponseEntity<CHistoriasClinicas>(hcService.guardarHC(historiaC),HttpStatus.CREATED);
	}
	
	@PutMapping("/actualizarHistoriasClinicas/{id}")
	public ResponseEntity<CHistoriasClinicas> actualizarHistoriasClinicas(@RequestBody CHistoriasClinicas hc ,@PathVariable Long id){
		return new ResponseEntity<CHistoriasClinicas>(hcService.actualizarHC(hc, id), HttpStatus.OK);
	}
	
}
