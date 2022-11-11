package com.ar.Grupo3.viewmodel;

import java.io.Serializable;
import java.util.Objects;

public class UsuarioModel implements Serializable {

    private static final long serialVersionUID = 7199798952321536520L;

    private Long idUsuario;

    private String nombreUsuario;

    private String contrasenia;

    private Long IdProvincia;

    private Integer cedula;

    private String nombre;

    private String apellido;

    private String celular;

    private String direccion;

    private String tel;

    private String Correo;

    // Not Mapped
    private String nombreProvincia;

    private String nombreRol;

    // Getters And Setters
    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public Long getIdProvincia() {
        return IdProvincia;
    }

    public void setIdProvincia(Long idProvincia) {
        IdProvincia = idProvincia;
    }

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setCorreo(String correo) {
        Correo = correo;
    }

    public String getNombreProvincia() {
        return nombreProvincia;
    }

    public void setNombreProvincia(String nombreProvincia) {
        this.nombreProvincia = nombreProvincia;
    }

    public String getNombreRol() {
        return nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    @Override
    public int hashCode() {
        return Objects.hash(Correo, IdProvincia, apellido, cedula, celular, contrasenia, direccion, idUsuario, nombre,
                nombreProvincia, nombreRol, nombreUsuario, tel);
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
        UsuarioModel other = (UsuarioModel) obj;
        return Objects.equals(Correo, other.Correo) && Objects.equals(IdProvincia, other.IdProvincia)
                && Objects.equals(apellido, other.apellido) && Objects.equals(cedula, other.cedula)
                && Objects.equals(celular, other.celular) && Objects.equals(contrasenia, other.contrasenia)
                && Objects.equals(direccion, other.direccion) && Objects.equals(idUsuario, other.idUsuario)
                && Objects.equals(nombre, other.nombre) && Objects.equals(nombreProvincia, other.nombreProvincia)
                && Objects.equals(nombreRol, other.nombreRol) && Objects.equals(nombreUsuario, other.nombreUsuario)
                && Objects.equals(tel, other.tel);
    }

    @Override
    public String toString() {
        return "UsuarioModel [idUsuario=" + idUsuario + ", nombreUsuario=" + nombreUsuario + ", contrasenia="
                + contrasenia + ", IdProvincia=" + IdProvincia + ", cedula=" + cedula + ", nombre=" + nombre
                + ", apellido=" + apellido + ", celular=" + celular + ", direccion=" + direccion + ", tel=" + tel
                + ", Correo=" + Correo + ", nombreProvincia=" + nombreProvincia + ", nombreRol=" + nombreRol + "]";
    }

}
