package com.ar.Grupo3.seguridad.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ar.Grupo3.seguridad.entity.Usuario;
import com.ar.Grupo3.seguridad.repositorios.UsuarioRepositorio;

@Service
public class UsuarioServiceDetalles implements UserDetailsService {

	@Autowired
	UsuarioRepositorio user;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuarioService = user.findByUsername(username);
		
		return UsuarioDetalles.build(usuarioService);
	}

}
