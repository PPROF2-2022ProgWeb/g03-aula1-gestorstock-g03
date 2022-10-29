package com.ar.Grupo3.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "tipo_prod")
public class TipoProducto implements Serializable {

	private static final long serialVersionUID = 995457594433365125L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "Id_Tipo_Prod")
	private Long idTipoProducto;

	@Column(name = "Nombre_Tipo_Prod")
	private String nombreTipoProd;

	public TipoProducto() {
		// Constructor Vacio
	}

	public TipoProducto(Long idTipoProducto) {
		super();
		this.idTipoProducto = idTipoProducto;
	}
	
	// Getters and Setters

	public Long getIdTipoProducto() {
		return idTipoProducto;
	}

	public void setIdTipoProducto(Long idTipoProducto) {
		this.idTipoProducto = idTipoProducto;
	}

	public String getNombreTipoProd() {
		return nombreTipoProd;
	}

	public void setNombreTipoProd(String nombreTipoProd) {
		this.nombreTipoProd = nombreTipoProd;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idTipoProducto, nombreTipoProd);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TipoProducto other = (TipoProducto) obj;
		return Objects.equals(idTipoProducto, other.idTipoProducto)
				&& Objects.equals(nombreTipoProd, other.nombreTipoProd);
	}

	@Override
	public String toString() {
		return "TipoProducto [idTipoProducto=" + idTipoProducto + ", nombreTipoProd=" + nombreTipoProd + "]";
	}

}
