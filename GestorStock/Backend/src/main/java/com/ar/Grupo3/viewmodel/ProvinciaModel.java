package com.ar.Grupo3.viewmodel;

import java.io.Serializable;
import java.util.Objects;

public class ProvinciaModel implements Serializable {

    private static final long serialVersionUID = 5671308365034304041L;

    private Long idProvincia;

    private String nombreProvincia;

    private Long idDepto;

    // Not Mapped
    private String nombreDepto;

    // Getters and Setters
    public Long getIdProvincia() {
        return idProvincia;
    }

    public void setIdProvincia(Long idProvincia) {
        this.idProvincia = idProvincia;
    }

    public String getNombreProvincia() {
        return nombreProvincia;
    }

    public void setNombreProvincia(String nombreProvincia) {
        this.nombreProvincia = nombreProvincia;
    }

    public Long getIdDepto() {
        return idDepto;
    }

    public void setIdDepto(Long idDepto) {
        this.idDepto = idDepto;
    }

    public String getNombreDepto() {
        return nombreDepto;
    }

    public void setNombreDepto(String nombreDepto) {
        this.nombreDepto = nombreDepto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDepto, idProvincia, nombreDepto, nombreProvincia);
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
        ProvinciaModel other = (ProvinciaModel) obj;
        return Objects.equals(idDepto, other.idDepto) && Objects.equals(idProvincia, other.idProvincia)
                && Objects.equals(nombreDepto, other.nombreDepto)
                && Objects.equals(nombreProvincia, other.nombreProvincia);
    }

    @Override
    public String toString() {
        return "ProvinciaModel [idProvincia=" + idProvincia + ", nombreProvincia=" + nombreProvincia + ", idDepto="
                + idDepto + ", nombreDepto=" + nombreDepto + "]";
    }

}
