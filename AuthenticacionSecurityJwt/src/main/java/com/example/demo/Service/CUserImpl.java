package com.example.demo.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Model.CUser;
import com.example.demo.Repository.IUserRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CUserImpl implements IUserService {

	@Autowired
	private IUserRepository iUserRepository;

	@Autowired
	private PasswordEncoder pass;

	@Override
	public List<CUser> listAllUsers() {
		return iUserRepository.findAll();
	}

	@Override
	public CUser createUser(CUser u) {
		if (!iUserRepository.findByEmail(u.getEmail()).isPresent() || !iUserRepository.findByUsername(u.getUsername()).isPresent()) {
			CUser us = CUser.builder()
					.name(u.getName())
					.lastname(u.getLastname())
					.email(u.getEmail())
					.username(u.getUsername())
					.password(pass.encode(u.getPassword()))
					.roles(u.getRoles()).build();
			return iUserRepository.save(us);
		}
		return null;
	}

	@Override
	public boolean deleteUser(Long id) {
		if (iUserRepository.findById(id).isPresent()) {
			iUserRepository.deleteById(id);
			return true;
		}
		return false;
	}

	@Override
	public CUser listFindByUsername(String username) {
		if (iUserRepository.findByUsername(username).isPresent()) {
			CUser uE = iUserRepository.findByUsername(username).get();
			return uE;
		}
		return null;
	}

	@Override
	public CUser updateUser(CUser u, Long id) {
		
		CUser usuarioE=iUserRepository.findById(id)
					.orElseThrow(() -> new EntityNotFoundException("El usuario con Id "+id+" No EXISTE¡"));
	
		usuarioE.setName    (u.getName()    !=null ? u.getName()    :usuarioE.getName());
		usuarioE.setLastname(u.getLastname()!=null ? u.getLastname():usuarioE.getLastname());
		usuarioE.setEmail   (u.getEmail()   !=null ? u.getEmail()   :usuarioE.getEmail());
		usuarioE.setUsername(u.getUsername()!=null ? u.getUsername():usuarioE.getUsername());
		usuarioE.setPassword(u.getPassword()!=null ? u.getPassword():usuarioE.getPassword());
		return iUserRepository.save(usuarioE);
	}

}
