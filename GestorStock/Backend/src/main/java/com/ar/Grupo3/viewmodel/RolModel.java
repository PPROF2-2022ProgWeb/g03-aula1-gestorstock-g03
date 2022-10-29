package com.ar.Grupo3.viewmodel;

import java.io.Serializable;
import java.util.Objects;

public class RolModel implements Serializable {

	private static final long serialVersionUID = -3946281183936522152L;

	private Long idRol;

	private Long idUsuario;

	private String nombreRol;

	// Not Mapped
	private String nombreUsuario;

	public RolModel() {
		// Constructor Vacio
	}

	// Getters and Setters

	public Long getIdRol() {
		return idRol;
	}

	public void setIdRol(Long idRol) {
		this.idRol = idRol;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getNombreRol() {
		return nombreRol;
	}

	public void setNombreRol(String nombreRol) {
		this.nombreRol = nombreRol;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idRol, idUsuario, nombreRol, nombreUsuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RolModel other = (RolModel) obj;
		return Objects.equals(idRol, other.idRol) && Objects.equals(idUsuario, other.idUsuario)
				&& Objects.equals(nombreRol, other.nombreRol) && Objects.equals(nombreUsuario, other.nombreUsuario);
	}

	@Override
	public String toString() {
		return "RolModel [idRol=" + idRol + ", idUsuario=" + idUsuario + ", nombreRol=" + nombreRol + ", nombreUsuario="
				+ nombreUsuario + "]";
	}
}
