package com.ar.Grupo3.seguridad.servicio;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;

import com.ar.Grupo3.seguridad.entity.Usuario;

public class UsuarioDetalles implements UserDetails {

	private static final long serialVersionUID = 1L;
	private String name;
	private String password;
	
	private Collection<? extends GrantedAuthority> autorizaciones;

	public UsuarioDetalles(String name, String password, Collection<? extends GrantedAuthority> autorizaciones) {
		super();
		this.name = name;
		this.password = password;
		this.autorizaciones = autorizaciones;
	}
	
	public static UsuarioDetalles build(Usuario u) {
		List<GrantedAuthority> autoriza = u.getRoles().stream()
				.map(rol -> new SimpleGrantedAuthority(rol.getNombreRol().name())).collect(Collectors.toList());
		
		return new UsuarioDetalles(u.getNombre(), u.getPassword(), autoriza);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return autorizaciones;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return name;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
