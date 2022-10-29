package com.ar.Grupo3.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "rol")
public class Rol implements Serializable {

	private static final long serialVersionUID = 8659264424060165903L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "Id_Rol")
	private Long idRol;
	
	@Column(name = "Id_Usuario")
	private Long idUsuario;

	@Column(name = "Nombre_Rol")
	private String nombreRol;
	
	public Rol() {
		// Constructor Vacio
	}

	public Rol(Long idRol) {
		super();
		this.idRol = idRol;
	}
	
	//Getters and Setters

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

	@Override
	public int hashCode() {
		return Objects.hash(idRol, idUsuario, nombreRol);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rol other = (Rol) obj;
		return Objects.equals(idRol, other.idRol) && Objects.equals(idUsuario, other.idUsuario)
				&& Objects.equals(nombreRol, other.nombreRol);
	}

	@Override
	public String toString() {
		return "Rol [idRol=" + idRol + ", idUsuario=" + idUsuario + ", nombreRol=" + nombreRol + "]";
	}
}
