package com.example.demo.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.Dto.AuthCreateUserRequest;
import com.example.demo.Dto.AuthLoginRequest;
import com.example.demo.Dto.AuthResponse;
import com.example.demo.Jwt.JwtUtils;
import com.example.demo.Model.CRol;
import com.example.demo.Model.CUser;
import com.example.demo.Repository.IRoleRepository;
import com.example.demo.Repository.IUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private PasswordEncoder pass;
	
	@Autowired
	private IRoleRepository iRoleRepository;
	
	@Autowired
 	private JwtUtils jwtUtils;
	
	@Autowired
	private IUserRepository iUserRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		CUser uEncontrado = iUserRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Usuario " + username + " no Encontrado¡¡¡¡"));

		List<SimpleGrantedAuthority> authoritiesList = new ArrayList<>();

		uEncontrado.getRoles()
				.forEach(r -> authoritiesList.add(new SimpleGrantedAuthority("ROLE_".concat(r.getNameRole()))));
		
		uEncontrado.getRoles().stream()
				.flatMap(rol -> rol.getPermission().stream())
				.forEach(p -> authoritiesList.add(new SimpleGrantedAuthority(p.getName())));
		

		return new User(uEncontrado.getUsername(), 
						uEncontrado.getPassword(), 
						true, 
						true, 
						true, 
						true, 
						authoritiesList);
	}
	
	//LOGIN
	public AuthResponse loginUser(AuthLoginRequest authLoginRequest) {
		String username= authLoginRequest.username();
		String password= authLoginRequest.password();
		
		Authentication authentication=this.authenticate(username,password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String accesToken=jwtUtils.createToken(authentication);
		AuthResponse authResponse=new AuthResponse(username, "Usuario Autentificado Correctamente", accesToken, true);
		return authResponse;
	}
	
	public Authentication authenticate(String username,String password) {
		UserDetails userDetails=this.loadUserByUsername(username);
		
		if(userDetails==null) {
			throw new BadCredentialsException("Usuario o Contraseña invalido¡");	
		}
		if(!pass.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("Contraseña Invalido");
		}
		return new UsernamePasswordAuthenticationToken(username  ,userDetails.getPassword(), userDetails.getAuthorities());
	}

	//REGISTRARSE
	public AuthResponse createUser(AuthCreateUserRequest authCreateUserRequest) {
		String name=authCreateUserRequest.name();
		String lastname=authCreateUserRequest.lastname();
		String email=authCreateUserRequest.email();
		String username=authCreateUserRequest.username();
		String password=authCreateUserRequest.password();
		List<String> roleRequest= authCreateUserRequest.roleRequest().roleListName();
		
		Set<CRol> roleSet=iRoleRepository.findCRolsByNameRoleIn(roleRequest).stream().collect(Collectors.toSet());
		
		if(roleSet.isEmpty()) {
			throw new IllegalArgumentException("Los roles indicados NO EXISTE¡.");
		}
		CUser uN=CUser.builder()
				.name(name)
				.lastname(lastname)
				.email(email)
				.username(username)
				.password(pass.encode(password))
				.roles(roleSet)
				.build();
		
		CUser uCreated= iUserRepository.save(uN);
		
		ArrayList<SimpleGrantedAuthority> authorityList=new ArrayList<>();
		
		uCreated.getRoles().forEach(r-> authorityList.add(new SimpleGrantedAuthority("ROLE_".concat(r.getNameRole()))));
		
		uCreated.getRoles().stream()
						.flatMap(r-> r.getPermission().stream())
						.forEach(p ->authorityList.add(new SimpleGrantedAuthority(p.getName())));
	
		
		Authentication authentication=new UsernamePasswordAuthenticationToken(uCreated.getUsername(), uCreated.getPassword(), authorityList);
		
		String accessToken = jwtUtils.createToken(authentication);
		AuthResponse authResponse=new AuthResponse(uCreated.getUsername(),"Usuario Creado Correctamente", accessToken, true);
		
		return authResponse;
		
	}

}
