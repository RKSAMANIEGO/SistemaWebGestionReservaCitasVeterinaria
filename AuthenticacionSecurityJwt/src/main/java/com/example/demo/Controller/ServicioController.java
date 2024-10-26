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

import com.example.demo.Dto.ActualizarServRequest;
import com.example.demo.Model.CServicios;
import com.example.demo.Service.IServiciosService;

@RestController
@RequestMapping("/servicios")
public class ServicioController {

	@Autowired
	private IServiciosService iServiciosService;
	
	
	@GetMapping("/listarServ")
	public ResponseEntity<List<CServicios>> listarServicios(){
		return new ResponseEntity<List<CServicios>>(iServiciosService.listarServicios(), HttpStatus.OK);
	}
	
	@GetMapping("/findByIdTipo/{id}")
	public ResponseEntity<List<CServicios>> buscarByIdTipo(@PathVariable Long id){
		return new ResponseEntity<List<CServicios>>(iServiciosService.buscarByIdTipo(id), HttpStatus.OK);
	}
	
	@PostMapping("/crearServ")
	public ResponseEntity<String> guardarServicio(@RequestBody CServicios s){
		if(iServiciosService.crearServicios(s)==null) {
			return new ResponseEntity<String>("El servicio ya Existe", HttpStatus.BAD_REQUEST);
		}
		iServiciosService.crearServicios(s);
		return new ResponseEntity<String>("Servicio Guardado", HttpStatus.CREATED);
	}
	
	@PutMapping("/actualizarServ/{id}")
	public ResponseEntity<ActualizarServRequest> actualizarServ(@RequestBody CServicios s,@PathVariable Long id){
		return new ResponseEntity<ActualizarServRequest>(iServiciosService.actualizarServicios(s, id), HttpStatus.OK);
	}
	
	@PatchMapping("/desactivarServ/{id}")
	public ResponseEntity<String> desactivarServ(@PathVariable Long id){
		iServiciosService.desactivarServicios(id);
		return new ResponseEntity<String>("Servicio Desactivado.", HttpStatus.OK);
	}
	
}
