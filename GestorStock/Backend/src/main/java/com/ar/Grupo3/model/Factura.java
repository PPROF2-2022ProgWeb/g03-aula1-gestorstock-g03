package com.ar.Grupo3.model;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "factura")
public class Factura implements Serializable {

    private static final long serialVersionUID = -794608157188325498L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Factura")
    private Long idFactura;

    @Column(name = "Nombre_Factura")
    private String nombreFactura;

    @Column(name = "Id_Provincia")
    private Long IdProvincia;

    @Column(name = "Id_Usuario")
    private Long idUsuario;

    @Column(name = "Total")
    private Double total;

    @Column(name = "Tipo")
    private Long tipo;

    @Column(name = "Fecha_Apertura")
    private Date fechaApertura;

    @Column(name = "Fecha_Cierre")
    private Date fechaCierre;

    @Column(name = "Dir_Envio")
    private String DirEnvio;

    public Factura() {
        // Constructor Vacio
    }

    // Getters and Setters
    public Factura(Long idFactura) {
        super();
        this.idFactura = idFactura;
    }

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

    @Override
    public int hashCode() {
        return Objects.hash(DirEnvio, IdProvincia, fechaApertura, fechaCierre, idFactura, idUsuario, nombreFactura,
                tipo, total);
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
        Factura other = (Factura) obj;
        return Objects.equals(DirEnvio, other.DirEnvio) && Objects.equals(IdProvincia, other.IdProvincia)
                && Objects.equals(fechaApertura, other.fechaApertura) && Objects.equals(fechaCierre, other.fechaCierre)
                && Objects.equals(idFactura, other.idFactura) && Objects.equals(idUsuario, other.idUsuario)
                && Objects.equals(nombreFactura, other.nombreFactura) && Objects.equals(tipo, other.tipo)
                && Objects.equals(total, other.total);
    }

    @Override
    public String toString() {
        return "Factura [idFactura=" + idFactura + ", nombreFactura=" + nombreFactura + ", IdProvincia=" + IdProvincia
                + ", idUsuario=" + idUsuario + ", total=" + total + ", tipo=" + tipo + ", fechaApertura="
                + fechaApertura + ", fechaCierre=" + fechaCierre + ", DirEnvio=" + DirEnvio + "]";
    }

}
