package com.ar.Grupo3.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "ventas")
public class Ventas implements Serializable {

	private static final long serialVersionUID = 8679204697818663646L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "Id_Venta")
	private Long idVenta;

	@Column(name = "Nombre_Venta")
	private String nombreVenta;

	@Column(name = "Id_Producto")
	private Long idProducto;

	@Column(name = "Id_Factura")
	private Long idFactura;

	@Column(name = "cantidad")
	private Long cantidad;

	@Column(name = "estado")
	private String estado;

	@Column(name = "Descuento")
	private Double descuento;

	public Ventas() {
		// Constructor Vacio
	}

	public Ventas(Long idVenta) {
		super();
		this.idVenta = idVenta;
	}

	// Getters and Setters

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

	@Override
	public int hashCode() {
		return Objects.hash(cantidad, descuento, estado, idFactura, idProducto, idVenta, nombreVenta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ventas other = (Ventas) obj;
		return Objects.equals(cantidad, other.cantidad) && Objects.equals(descuento, other.descuento)
				&& Objects.equals(estado, other.estado) && Objects.equals(idFactura, other.idFactura)
				&& Objects.equals(idProducto, other.idProducto) && Objects.equals(idVenta, other.idVenta)
				&& Objects.equals(nombreVenta, other.nombreVenta);
	}

	@Override
	public String toString() {
		return "Ventas [idVenta=" + idVenta + ", nombreVenta=" + nombreVenta + ", idProducto=" + idProducto
				+ ", idFactura=" + idFactura + ", cantidad=" + cantidad + ", estado=" + estado + ", descuento="
				+ descuento + "]";
	}

}
