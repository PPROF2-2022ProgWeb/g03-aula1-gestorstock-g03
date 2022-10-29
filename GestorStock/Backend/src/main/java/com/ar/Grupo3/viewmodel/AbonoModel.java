package com.ar.Grupo3.viewmodel;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class AbonoModel implements Serializable {

	private static final long serialVersionUID = 6291156987645504567L;
	
	private Long idAbono;

	private String nombreAbono;

	private Date fecha;

	private Double valor;

	private Long idFactura;

	// No Mapped
	private String nombreFactura;

	public AbonoModel() {
		// Constructor Vacio
	}

	// Getters and Setters

	public Long getIdAbono() {
		return idAbono;
	}

	public void setIdAbono(Long idAbono) {
		this.idAbono = idAbono;
	}

	public String getNombreAbono() {
		return nombreAbono;
	}

	public void setNombreAbono(String nombreAbono) {
		this.nombreAbono = nombreAbono;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Long getIdFactura() {
		return idFactura;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public void setIdFactura(Long idFactura) {
		this.idFactura = idFactura;
	}

	public String getNombreFactura() {
		return nombreFactura;
	}

	public void setNombreFactura(String nombreFactura) {
		this.nombreFactura = nombreFactura;
	}

	@Override
	public int hashCode() {
		return Objects.hash(fecha, idAbono, idFactura, nombreAbono, nombreFactura, valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AbonoModel other = (AbonoModel) obj;
		return Objects.equals(fecha, other.fecha) && Objects.equals(idAbono, other.idAbono)
				&& Objects.equals(idFactura, other.idFactura) && Objects.equals(nombreAbono, other.nombreAbono)
				&& Objects.equals(nombreFactura, other.nombreFactura) && Objects.equals(valor, other.valor);
	}

	@Override
	public String toString() {
		return "AbonoModel [idAbono=" + idAbono + ", nombreAbono=" + nombreAbono + ", fecha=" + fecha + ", valor="
				+ valor + ", idFactura=" + idFactura + ", nombreFactura=" + nombreFactura + "]";
	}

	

}
