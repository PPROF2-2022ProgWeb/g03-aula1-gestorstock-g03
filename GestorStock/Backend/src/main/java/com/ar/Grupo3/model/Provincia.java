package com.ar.Grupo3.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "provincia")
public class Provincia implements Serializable {

	private static final long serialVersionUID = 2245709417864108203L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "Id_Provincia")
	private Long idProvincia;

	@Column(name = "Nombre_Provincia")
	private String nombreProvincia;

	@Column(name = "Id_Depto")
	private Long idDepto;

	public Provincia() {
		// Constructor Vacio
	}

	// Getters and Setters

	public Provincia(Long idProvincia) {
		super();
		this.idProvincia = idProvincia;
	}

	public Long getIdProvincia() {
		return idProvincia;
	}

	public void setIdProvincia(Long idProvincia) {
		this.idProvincia = idProvincia;
	}

	public String getNombreProvincia() {
		return nombreProvincia;
	}

	public void setNombreProvincia(String nombreProvincia) {
		this.nombreProvincia = nombreProvincia;
	}

	public Long getIdDepto() {
		return idDepto;
	}

	public void setIdDepto(Long idDepto) {
		this.idDepto = idDepto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idDepto, idProvincia, nombreProvincia);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Provincia other = (Provincia) obj;
		return Objects.equals(idDepto, other.idDepto) && Objects.equals(idProvincia, other.idProvincia)
				&& Objects.equals(nombreProvincia, other.nombreProvincia);
	}

	@Override
	public String toString() {
		return "Provincia [idProvincia=" + idProvincia + ", nombreProvincia=" + nombreProvincia + ", idDepto=" + idDepto
				+ "]";
	}

}
