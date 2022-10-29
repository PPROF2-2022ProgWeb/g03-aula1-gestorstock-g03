package com.ar.Grupo3.viewmodel;

import java.io.Serializable;
import java.util.Objects;


public class VentasModel implements Serializable {

	private static final long serialVersionUID = -7936555533593536254L;

	private Long idVenta;

	private String nombreVenta;
	
	private Long idProducto;
	
	private Long idFactura;

	private Long cantidad;

	private String estado;

	private Double descuento;
	
	//Not Mapped
	private String nombreProducto;
	
	private String nombreFactura;

	//Getters and Setters

	public Long getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(Long idVenta) {
		this.idVenta = idVenta;
	}

	public String getNombreVenta() {
		return nombreVenta;
	}

	public void setNombreVenta(String nombreVenta) {
		this.nombreVenta = nombreVenta;
	}

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public Long getIdFactura() {
		return idFactura;
	}

	public void setIdFactura(Long idFactura) {
		this.idFactura = idFactura;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Double getDescuento() {
		return descuento;
	}

	public void setDescuento(Double descuento) {
		this.descuento = descuento;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public String getNombreFactura() {
		return nombreFactura;
	}

	public void setNombreFactura(String nombreFactura) {
		this.nombreFactura = nombreFactura;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cantidad, descuento, estado, idFactura, idProducto, idVenta, nombreFactura, nombreProducto,
				nombreVenta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VentasModel other = (VentasModel) obj;
		return Objects.equals(cantidad, other.cantidad) && Objects.equals(descuento, other.descuento)
				&& Objects.equals(estado, other.estado) && Objects.equals(idFactura, other.idFactura)
				&& Objects.equals(idProducto, other.idProducto) && Objects.equals(idVenta, other.idVenta)
				&& Objects.equals(nombreFactura, other.nombreFactura)
				&& Objects.equals(nombreProducto, other.nombreProducto)
				&& Objects.equals(nombreVenta, other.nombreVenta);
	}

	@Override
	public String toString() {
		return "VentasModel [idVenta=" + idVenta + ", nombreVenta=" + nombreVenta + ", idProducto=" + idProducto
				+ ", idFactura=" + idFactura + ", cantidad=" + cantidad + ", estado=" + estado + ", descuento="
				+ descuento + ", nombreProducto=" + nombreProducto + ", nombreFactura=" + nombreFactura + "]";
	}
}
