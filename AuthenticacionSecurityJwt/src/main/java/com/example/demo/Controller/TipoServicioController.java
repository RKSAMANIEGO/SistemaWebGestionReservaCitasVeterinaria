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

import com.example.demo.Model.CTipoServicio;
import com.example.demo.Service.ITipoServicio;

@RestController
@RequestMapping("/tipoServicio")
public class TipoServicioController {

	@Autowired
	private ITipoServicio iTipoServicio;
	
	
	@GetMapping("/listar")
	public ResponseEntity<List<CTipoServicio>> listarTipoServ(){
		return new ResponseEntity<List<CTipoServicio>>(iTipoServicio.listarTipoServicio(), HttpStatus.OK);
	}
	
	@PostMapping("/guardar")
	public ResponseEntity<String> guardarTipoServ(@RequestBody CTipoServicio ts){
		if(iTipoServicio.guardarTipoServicio(ts)==null) {
			return new ResponseEntity<String>("El nombre ya Existe!", HttpStatus.BAD_REQUEST);
		}
		iTipoServicio.guardarTipoServicio(ts);
		return new ResponseEntity<String>("Registro Guardado.", HttpStatus.CREATED);
	}
	
	
	@PutMapping("/actualizar/{id}")
	public ResponseEntity<CTipoServicio> actualizarTipoServ(@RequestBody CTipoServicio ts,@PathVariable Long id){
		return new ResponseEntity<CTipoServicio>(iTipoServicio.actualizarTipoServicio(ts, id), HttpStatus.OK);
	}
	
	@PatchMapping("/desactivar/{id}")
	public ResponseEntity<String> desactivarTipoServ(@PathVariable Long id){
		iTipoServicio.desactivarTipoServicio(id);
		return new ResponseEntity<String>("Tipo Servicio con Id "+id+" se Desactivo.", HttpStatus.OK);
	}
	
}
