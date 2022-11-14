package com.ar.Grupo3.viewmodel;

import java.io.Serializable;
import java.util.Objects;

public class ProvinciaModel implements Serializable {

    private static final long serialVersionUID = 5671308365034304041L;

    private Long idProvincia;

    private String nombreProvincia;


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

    @Override
    public int hashCode() {
        return Objects.hash(idProvincia, nombreProvincia);
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
        return Objects.equals(idProvincia, other.idProvincia)
                && Objects.equals(nombreProvincia, other.nombreProvincia);
    }

    @Override
    public String toString() {
        return "ProvinciaModel [idProvincia=" + idProvincia + ", nombreProvincia=" + nombreProvincia + "]";
    }

}
