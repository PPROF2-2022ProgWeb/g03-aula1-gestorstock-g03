package com.ar.Grupo3.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "producto")
public class Producto implements Serializable {

	private static final long serialVersionUID = 6094366348151550401L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "Id_Producto")
	private Long idProducto;

	@Column(name = "Nombre_Producto")
	private String nombreProducto;

	@Column(name = "Id_Tipo_Prod")
	private Long idTipoProd;

	@Column(name = "Clave")
	private String clave;

	@Column(name = "Barcode")
	private String barCode;

	@Column(name = "Image_Url")
	private String imageURL;

	@Column(name = "Costo")
	private Double costo;

	@Column(name = "Valor")
	private Double valor;

	@Column(name = "cantidad")
	private Long cantidad;

	@Column(name = "Fecha_Ing")
	private Date fechaIng;

	public Producto() {
		// Consructor vacio
	}

	public Producto(Long idProducto) {
		super();
		this.idProducto = idProducto;
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

	@Override
	public int hashCode() {
		return Objects.hash(barCode, cantidad, clave, costo, fechaIng, idProducto, idTipoProd, imageURL, nombreProducto,
				valor);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Producto other = (Producto) obj;
		return Objects.equals(barCode, other.barCode) && Objects.equals(cantidad, other.cantidad)
				&& Objects.equals(clave, other.clave) && Objects.equals(costo, other.costo)
				&& Objects.equals(fechaIng, other.fechaIng) && Objects.equals(idProducto, other.idProducto)
				&& Objects.equals(idTipoProd, other.idTipoProd) && Objects.equals(imageURL, other.imageURL)
				&& Objects.equals(nombreProducto, other.nombreProducto) && Objects.equals(valor, other.valor);
	}

	@Override
	public String toString() {
		return "Producto [idProducto=" + idProducto + ", nombreProducto=" + nombreProducto + ", idTipoProd="
				+ idTipoProd + ", clave=" + clave + ", barCode=" + barCode + ", imageURL=" + imageURL + ", costo="
				+ costo + ", valor=" + valor + ", cantidad=" + cantidad + ", fechaIng=" + fechaIng + "]";
	}

}
