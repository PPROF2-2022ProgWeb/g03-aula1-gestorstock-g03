package com.ar.Grupo3.viewmodel;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class ProductoModel implements Serializable {

	private static final long serialVersionUID = 2694838481482894431L;

	private Long idProducto;

	private String nombreProducto;

	private Long idTipoProd;

	private String clave;

	private String barCode;

	private String imageURL;

	private Double costo;

	private Double valor;

	private Long cantidad;

	private Date fechaIng;

	// No Mapped
	private String tipoProd;

	public ProductoModel() {
		// Constructor Vacio
	}

	// Getters and Setters

	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public Long getIdTipoProd() {
		return idTipoProd;
	}

	public void setIdTipoProd(Long idTipoProd) {
		this.idTipoProd = idTipoProd;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public Double getCosto() {
		return costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Long getCantidad() {
		return cantidad;
	}

	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechaIng() {
		return fechaIng;
	}

	public void setFechaIng(Date fechaIng) {
		this.fechaIng = fechaIng;
	}

	public String getTipoProd() {
		return tipoProd;
	}

	public void setTipoProd(String tipoProd) {
		this.tipoProd = tipoProd;
	}

	@Override
	public int hashCode() {
		return Objects.hash(barCode, cantidad, clave, costo, fechaIng, idProducto, idTipoProd, imageURL, nombreProducto,
				tipoProd, valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ProductoModel other = (ProductoModel) obj;
		return Objects.equals(barCode, other.barCode) && Objects.equals(cantidad, other.cantidad)
				&& Objects.equals(clave, other.clave) && Objects.equals(costo, other.costo)
				&& Objects.equals(fechaIng, other.fechaIng) && Objects.equals(idProducto, other.idProducto)
				&& Objects.equals(idTipoProd, other.idTipoProd) && Objects.equals(imageURL, other.imageURL)
				&& Objects.equals(nombreProducto, other.nombreProducto) && Objects.equals(tipoProd, other.tipoProd)
				&& Objects.equals(valor, other.valor);
	}

	@Override
	public String toString() {
		return "ProductoModel [idProducto=" + idProducto + ", nombreProducto=" + nombreProducto + ", idTipoProd="
				+ idTipoProd + ", clave=" + clave + ", barCode=" + barCode + ", imageURL=" + imageURL + ", costo="
				+ costo + ", valor=" + valor + ", cantidad=" + cantidad + ", fechaIng=" + fechaIng + ", tipoProd="
				+ tipoProd + "]";
	}

}
