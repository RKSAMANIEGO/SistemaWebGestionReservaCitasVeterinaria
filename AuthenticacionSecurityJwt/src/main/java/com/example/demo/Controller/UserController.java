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

import com.example.demo.Model.CUser;
import com.example.demo.Service.IUserService;

@RestController
@RequestMapping("/usuario")
public class UserController {

	@Autowired
	private IUserService iUserService;

	@GetMapping("/findByUsername/{username}")
	public ResponseEntity<CUser> UserFindByUsername(@PathVariable String username) {
		return new ResponseEntity<CUser>(iUserService.listFindByUsername(username), HttpStatus.OK);
	}

	@GetMapping("/public")
	public ResponseEntity<String> greetingPublic() {
		return new ResponseEntity<String>("Welcome to Spring Boot", HttpStatus.OK);
	}

	@GetMapping("/private")
	public ResponseEntity<String> greetingPrivate() {
		return new ResponseEntity<String>("Welcome to Spring Security, ADMINISTRADOR", HttpStatus.OK);
	}
	@GetMapping("/listAll")
	public ResponseEntity<List<CUser>> listUser() {
		return new ResponseEntity<List<CUser>>(iUserService.listAllUsers(), HttpStatus.OK);
	}

	@PostMapping("/create")
	public ResponseEntity<String> createUser(@RequestBody CUser u) {
		if (iUserService.createUser(u) == null) {
			return new ResponseEntity<String>("Correo del Usuario ya EXISTE", HttpStatus.NOT_ACCEPTABLE);
		}
		iUserService.createUser(u);
		return new ResponseEntity<String>("Usuario Creado", HttpStatus.OK);
	}
	
	@PutMapping("/update/{id}")
	public ResponseEntity<CUser> updateUsuario(@RequestBody CUser u, @PathVariable Long id){
		return new ResponseEntity<CUser>(iUserService.updateUser(u, id) , HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable Long id) {
		if (iUserService.deleteUser(id) == true) {
			iUserService.deleteUser(id);
			return new ResponseEntity<String>("Usuario con ID " + id + " ELIMINADO CORRECTAMENTE.", HttpStatus.OK);
		}
		return new ResponseEntity<String>("Usuario con ID " + id + " NO ENCONTRADO.", HttpStatus.NOT_FOUND);
	}

}
