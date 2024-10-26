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

import com.example.demo.Dto.VentaGuardarResponse;
import com.example.demo.Model.CVentas;
import com.example.demo.Service.IVentasService;

@RestController
@RequestMapping("/ventas")
public class VentasController {

	@Autowired
	private IVentasService ventasService;

	@GetMapping("/listarVentas")
	public ResponseEntity<List<CVentas>> listarVentas(){		
		return new ResponseEntity<List<CVentas>>(ventasService.listarVentas(), HttpStatus.OK);
	}
	
	
	@PostMapping("/guardarVenta")
	public ResponseEntity<VentaGuardarResponse> guardarVenta(@RequestBody CVentas v){
		if(v.getArticulo().getId()==null || v.getCliente().getId()==null) {
			return new ResponseEntity<VentaGuardarResponse>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<VentaGuardarResponse>(ventasService.guardarVentas(v), HttpStatus.CREATED);
	}
	
	@PutMapping("/actualizarVenta/{id}")
	public ResponseEntity<CVentas> actualizarVenta(@RequestBody CVentas v,@PathVariable Long id){
		return new ResponseEntity<CVentas>(ventasService.actualizarVentas(v, id), HttpStatus.OK);
	}

	@DeleteMapping("/cancelarVenta/{id}")
	public ResponseEntity<String> cancelarVenta(@PathVariable Long id){
		return new ResponseEntity<String>(ventasService.cancelarVentas(id), HttpStatus.OK);
	}
	
	

}
