package com.example.demo.Service.Impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Model.CUser;
import com.example.demo.Model.CVeterinario;
import com.example.demo.Repository.IUserRepository;
import com.example.demo.Repository.IVeterinarioRepository;
import com.example.demo.Service.IVeterinarioService;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class VeterinarioImpl implements IVeterinarioService{

	@Autowired
	private IVeterinarioRepository vR;
	
	@Autowired
	private IUserRepository uR;
	
	@Autowired
	private PasswordEncoder pass;
	
	@Override
	public List<CVeterinario> listarVeterinario() {
		return vR.findAll();
	}

	@Override
	public CVeterinario guardarVeterinario(CVeterinario v) {
		
		if(!vR.findByDocumento(v.getDocumento()).isPresent() && !vR.findByEmail(v.getEmail()).isPresent() ) {
			CVeterinario vN= CVeterinario.builder()
					.nombres(v.getNombres())
					.documento(v.getDocumento())
					.especialidad(v.getEspecialidad())
					.telefono(v.getTelefono())
					.email(v.getEmail())
					.fechaRegistro(LocalDate.now())
					.imagen(v.getImagen())
					.build();
		
	
			
			Optional<CUser> uEncontrado=uR.findById(v.getUsuario().getId());
			v.getUsuario().setPassword(pass.encode(v.getUsuario().getPassword()));
			vN.setUsuario(!uEncontrado.isPresent()? uR.save(v.getUsuario()) : uEncontrado.get());
		
			return vR.save(vN);
			
		}
		
		log.info("El documento "+v.getDocumento()+" Ya se Encuntra Registrado");
		log.info("El correo "+ v.getEmail()+" Ya se Encuntra Registrado");
		return null;
	}

	@Override
	public CVeterinario actualizarVeterinario(CVeterinario v, Long idVeterinario) {
		CVeterinario vE=vR.findById(idVeterinario).orElseThrow(() -> new EntityNotFoundException("Veterinario no Existe¡..."));
		
		vE.setNombres     (v.getNombres()      != null ? v.getNombres()      : vE.getNombres());
		vE.setDocumento   (v.getDocumento()    != null ? v.getDocumento()    : vE.getDocumento());
		vE.setEspecialidad(v.getEspecialidad() != null ? v.getEspecialidad() : vE.getEspecialidad());
		vE.setTelefono    (v.getTelefono()     != null ? v.getTelefono()     : vE.getTelefono());
		vE.setEmail       (v.getEmail()        != null ? v.getEmail()        : vE.getEmail());
		vE.setImagen      (v.getImagen()       != null ? v.getImagen()       : vE.getImagen());
		
		CUser usuarioE=uR.findById(vE.getUsuario().getId()).orElseThrow(() -> new EntityNotFoundException("Usuario no Existe¡..."));
		
	
		if (v.getUsuario() != null) {
		usuarioE.setName    (v.getUsuario().getName()     == null ? usuarioE.getName()     : v.getUsuario().getName() );
		usuarioE.setLastname(v.getUsuario().getLastname() == null ? usuarioE.getLastname() : v.getUsuario().getLastname() );
		usuarioE.setEmail   (v.getUsuario().getEmail()    == null ? usuarioE.getEmail()    : v.getUsuario().getEmail());
		usuarioE.setUsername(v.getUsuario().getUsername() == null ? usuarioE.getUsername() : v.getUsuario().getUsername());
		usuarioE.setPassword(v.getUsuario().getPassword() == null ? usuarioE.getPassword(): pass.encode(v.getUsuario().getPassword()));
		CUser uActualizado= uR.save(usuarioE);
		vE.setUsuario(uActualizado);
		}
		
		return vR.save(vE);
	}

	@Override
	public CVeterinario desactivarVeterinario(Long id) {
		CVeterinario vE=vR.findById(id).orElseThrow(()-> new EntityNotFoundException("Veterinario no Existe¡..") );
		vE.setEstado(false);
		return vR.save(vE);
	}

}
