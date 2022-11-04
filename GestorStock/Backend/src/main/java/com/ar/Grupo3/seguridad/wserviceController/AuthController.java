package com.ar.Grupo3.seguridad.wserviceController;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

import com.ar.Grupo3.seguridad.Enums.NombresRol;
import com.ar.Grupo3.seguridad.dto.JWTDTO;
import com.ar.Grupo3.seguridad.dto.LoginUsuario;
import com.ar.Grupo3.seguridad.dto.NuevoUsuario;
import com.ar.Grupo3.seguridad.entity.Rol;
import com.ar.Grupo3.seguridad.entity.Usuario;
import com.ar.Grupo3.seguridad.jwt.JWTProvider;
import com.ar.Grupo3.seguridad.negocio.DaoRolImpl;
import com.ar.Grupo3.seguridad.negocio.DaoUsuarioImpl;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	AuthenticationManager authenticationManagerBean;
	@Autowired
	DaoRolImpl rolService;
	@Autowired
	DaoUsuarioImpl usuarioService;
	@Autowired
	JWTProvider jwtProvider;
	
	@PostMapping("/nuevo")
	public ResponseEntity<?> nuevo(@Valid @RequestBody NuevoUsuario nuevoUsuario, BindingResult bindingResult ){
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<Object>( new Mensaje("Campos Mal colocados"), HttpStatus.BAD_REQUEST);
		}
		if(usuarioService.existeNombreUsuario(nuevoUsuario.getUsername())) {
			return new ResponseEntity<Object>(new Mensaje("Ese nombre de usuario ya existe"), HttpStatus.BAD_REQUEST);
		}
		Usuario us = new Usuario(nuevoUsuario.getUsername(), passwordEncoder.encode(nuevoUsuario.getPassword()));
		
		Set<Rol> roles = new HashSet<>();
		roles.add(rolService.obtenerNombreRol(NombresRol.ROLE_USER).get());
		
		if (nuevoUsuario.getRoles().contains("ADMIN")) {
			roles.add(rolService.obtenerNombreRol(NombresRol.ROLE_ADMIN).get());
			us.setRoles(roles);
			usuarioService.agregar(us);;
		}
		
		return new ResponseEntity<Object>(new Mensaje("Usuario ha sido guardado"),HttpStatus.CREATED);
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@PostMapping("/login")
	public ResponseEntity<JWTDTO> login(@Valid @RequestBody LoginUsuario loginUsuario, BindingResult bindingResult ){
		if (bindingResult.hasErrors()) {
			return new ResponseEntity(new Mensaje("Campos erroneos"),HttpStatus.BAD_REQUEST);
		}
		Authentication autenticacion = authenticationManagerBean.authenticate(new UsernamePasswordAuthenticationToken(
				loginUsuario.getUsername(), loginUsuario.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(autenticacion);
		String jwt = jwtProvider.generateToken(autenticacion);
		UserDetails userDetails = (UserDetails) autenticacion.getPrincipal();
		
		JWTDTO jwtDto = new JWTDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		return new ResponseEntity(jwtDto,HttpStatus.OK);
	}
	
}
