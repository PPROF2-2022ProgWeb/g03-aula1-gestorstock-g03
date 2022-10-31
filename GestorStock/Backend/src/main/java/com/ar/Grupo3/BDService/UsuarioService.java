package com.ar.Grupo3.BDService;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import com.ar.Grupo3.data.objects.repositorio.UsuarioRepositorio;
import com.ar.Grupo3.model.Rol;
import com.ar.Grupo3.model.Usuario;
import org.springframework.transaction.annotation.Transactional;

@Service("UserDetailsService")
public class UsuarioService implements UserDetailsService {

	@Autowired
	private UsuarioRepositorio dao;

	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Usuario usuario = dao.findByUsername(username);

		if (usuario == null) {

		}

		if (usuario == null) {
			throw new UsernameNotFoundException(username);
		}

		var roles = new ArrayList<GrantedAuthority>();

		for (Rol rol : usuario.getRoles()) {
			roles.add(new SimpleGrantedAuthority(rol.getNombreRol()));
		}

		return new User(usuario.getUsername(), usuario.getPassword(), roles);
	}

}
