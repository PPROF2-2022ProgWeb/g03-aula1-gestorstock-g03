package com.ar.Grupo3.seguridad.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class JWTDTO {

	private String token;
	private String bearer = "Bearer";
	private String username;
	private Collection<? extends GrantedAuthority> autorizaciones;

	public JWTDTO(String token, String username, Collection<? extends GrantedAuthority> autorizaciones) {
		this.token = token;
		this.username = username;
		this.autorizaciones = autorizaciones;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getBearer() {
		return bearer;
	}

	public void setBearer(String bearer) {
		this.bearer = bearer;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Collection<? extends GrantedAuthority> getAutorizaciones() {
		return autorizaciones;
	}

	public void setAutorizaciones(Collection<? extends GrantedAuthority> autorizaciones) {
		this.autorizaciones = autorizaciones;
	}
}
