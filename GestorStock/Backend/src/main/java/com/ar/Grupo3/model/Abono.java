package com.ar.Grupo3.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "abono")
public class Abono implements Serializable {

    private static final long serialVersionUID = -2704070408662157289L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Abono")
    private Long idAbono;

    @Column(name = "Nombre_Abono")
    private String nombreAbono;

    @Column(name = "Fecha")
    private Date fecha;

    @Column(name = "Valor")
    private Double valor;

    @Column(name = "Id_Factura")
    private Long idFactura;

    public Abono() {
        // Constructor Vacio
    }

    public Abono(Long idAbono) {
        super();
        this.idAbono = idAbono;
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

    @Override
    public int hashCode() {
        return Objects.hash(fecha, idAbono, idFactura, nombreAbono, valor);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Abono other = (Abono) obj;
        return Objects.equals(fecha, other.fecha) && Objects.equals(idAbono, other.idAbono)
                && Objects.equals(idFactura, other.idFactura) && Objects.equals(nombreAbono, other.nombreAbono)
                && Objects.equals(valor, other.valor);
    }

    @Override
    public String toString() {
        return "Abono [idAbono=" + idAbono + ", nombreAbono=" + nombreAbono + ", fecha=" + fecha + ", valor=" + valor
                + ", idFactura=" + idFactura + "]";
    }
}
