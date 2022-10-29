package com.ar.Grupo3.viewmodel;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class FacturaModel implements Serializable {

	private static final long serialVersionUID = 6704044752201791911L;

	private Long idFactura;

	private String nombreFactura;

	private Long IdProvincia;

	private Long idUsuario;

	private Double total;

	private Long tipo;

	private Date fechaApertura;

	private Date fechaCierre;

	private String DirEnvio;

	// Not Mapped
	private String nombreProvincia;

	private String nombreUsuario;

	public FacturaModel() {
		// Constructor Vacio
	}

	// Getters and Setters

	public Long getIdFactura() {
		return idFactura;
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

	public Long getIdProvincia() {
		return IdProvincia;
	}

	public void setIdProvincia(Long idProvincia) {
		IdProvincia = idProvincia;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Long getTipo() {
		return tipo;
	}

	public void setTipo(Long tipo) {
		this.tipo = tipo;
	}

	public Date getFechaApertura() {
		return fechaApertura;
	}

	public void setFechaApertura(Date fechaApertura) {
		this.fechaApertura = fechaApertura;
	}

	public Date getFechaCierre() {
		return fechaCierre;
	}

	public void setFechaCierre(Date fechaCierre) {
		this.fechaCierre = fechaCierre;
	}

	public String getDirEnvio() {
		return DirEnvio;
	}

	public void setDirEnvio(String dirEnvio) {
		DirEnvio = dirEnvio;
	}

	public String getNombreProvincia() {
		return nombreProvincia;
	}

	public void setNombreProvincia(String nombreProvincia) {
		this.nombreProvincia = nombreProvincia;
	}

	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(DirEnvio, IdProvincia, fechaApertura, fechaCierre, idFactura, idUsuario, nombreFactura,
				nombreProvincia, nombreUsuario, tipo, total);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FacturaModel other = (FacturaModel) obj;
		return Objects.equals(DirEnvio, other.DirEnvio) && Objects.equals(IdProvincia, other.IdProvincia)
				&& Objects.equals(fechaApertura, other.fechaApertura) && Objects.equals(fechaCierre, other.fechaCierre)
				&& Objects.equals(idFactura, other.idFactura) && Objects.equals(idUsuario, other.idUsuario)
				&& Objects.equals(nombreFactura, other.nombreFactura)
				&& Objects.equals(nombreProvincia, other.nombreProvincia)
				&& Objects.equals(nombreUsuario, other.nombreUsuario) && Objects.equals(tipo, other.tipo)
				&& Objects.equals(total, other.total);
	}

	@Override
	public String toString() {
		return "FacturaModel [idFactura=" + idFactura + ", nombreFactura=" + nombreFactura + ", IdProvincia="
				+ IdProvincia + ", idUsuario=" + idUsuario + ", total=" + total + ", tipo=" + tipo + ", fechaApertura="
				+ fechaApertura + ", fechaCierre=" + fechaCierre + ", DirEnvio=" + DirEnvio + ", nombreProvincia="
				+ nombreProvincia + ", nombreUsuario=" + nombreUsuario + "]";
	}

}
