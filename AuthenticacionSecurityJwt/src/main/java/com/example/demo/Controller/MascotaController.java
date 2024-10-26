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

import com.example.demo.Dto.CreateMascotaDto;
import com.example.demo.Model.CCliente;
import com.example.demo.Model.CMascota;
import com.example.demo.Service.IClienteService;
import com.example.demo.Service.IMascotaService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/mascota")
public class MascotaController {

	@Autowired
	private IMascotaService mascotaService;
	@Autowired
	private IClienteService clienteService;

	@GetMapping("/listarMascotasActivas")
	public ResponseEntity<List<CMascota>> listarMascotaActivas(){
		return new ResponseEntity<List<CMascota>>(mascotaService.listarMascotasActivas(), HttpStatus.OK);
	}
	
	@GetMapping("/listarMascotasInactivas")
	public ResponseEntity<List<CMascota>> listarMascotasInactivas(){
		return new ResponseEntity<List<CMascota>>(mascotaService.listarMascotasInactivas(), HttpStatus.OK);
	}
	
	@GetMapping("/buscarMascotaByNomCliente/{estado}/{nomCliente}")
	public ResponseEntity<List<CMascota>> buscarMascotaByNombreCliente(@PathVariable Integer estado,@PathVariable String nomCliente){
		return new ResponseEntity<List<CMascota>>(mascotaService.findMascotaByNombreCliente(estado, nomCliente), HttpStatus.OK);
	}
	
	@GetMapping("/buscarNombreRazaSexo/{estado}/{valor}")
	public ResponseEntity<List<CMascota>> findByNombreRazaSex(@PathVariable Integer estado ,@PathVariable String valor){
		return new ResponseEntity<List<CMascota>>(mascotaService.findByNombreRazaSexo(estado,valor), HttpStatus.OK);
	}
	
	@PostMapping("/guardarMascota/{id}")
	public ResponseEntity<String> guardarMascota(@Valid @RequestBody CreateMascotaDto m ,@PathVariable Long id ){
		CCliente cliente=clienteService.findById(id)
				.orElseThrow(() -> new EntityNotFoundException("Cliente no Existe"));
			
	         	mascotaService.guardarMascota(m, cliente);	
	         	return new ResponseEntity<String>("Mascota Creado Correctamente", HttpStatus.CREATED);
	}
	
	
	@PutMapping("/actualizar/{idCliente}")
	public ResponseEntity<CMascota> actualizarMascota(@RequestBody CMascota m,@PathVariable Long idCliente ){
		if(mascotaService.actualizarMascota(m, idCliente)==null) {
			return new ResponseEntity<CMascota>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<CMascota>(mascotaService.actualizarMascota(m, idCliente), HttpStatus.OK);
	}
	
	
	@PatchMapping("/desactivarMascota/{id}")
	public ResponseEntity<String> desactivarMascota(@PathVariable Long id){
		 mascotaService.desactivarMascota(id);
		 return new ResponseEntity<String>("Mascota Desactivada", HttpStatus.OK);
	}

}
