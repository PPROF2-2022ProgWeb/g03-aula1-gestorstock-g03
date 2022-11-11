package com.ar.Grupo3.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.*;

@Entity
@Table(name = "departamento")
public class Departamento implements Serializable {

    private static final long serialVersionUID = 2450755949774311057L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id_Depto")
    private Long idDepto;

    @Column(name = "Nombre_Depto")
    private String depto;

    public Departamento() {
        // Constructor vacio
    }

    public Departamento(Long idDepto) {
        super();
        this.idDepto = idDepto;
    }

    // Getters and Setters
    public Long getIdDepto() {
        return idDepto;
    }

    public void setIdDepto(Long idDepto) {
        this.idDepto = idDepto;
    }

    public String getDepto() {
        return depto;
    }

    public void setDepto(String depto) {
        this.depto = depto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(depto, idDepto);
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
        Departamento other = (Departamento) obj;
        return Objects.equals(depto, other.depto) && Objects.equals(idDepto, other.idDepto);
    }

    @Override
    public String toString() {
        return "Departamento [idDepto=" + idDepto + ", depto=" + depto + "]";
    }

}
