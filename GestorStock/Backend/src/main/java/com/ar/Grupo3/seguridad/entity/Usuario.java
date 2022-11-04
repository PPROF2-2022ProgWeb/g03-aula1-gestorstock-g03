package com.ar.Grupo3.seguridad.entity;

import java.io.Serializable;
import java.util.*;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
public class Usuario implements Serializable {

	private static final long serialVersionUID = 2455537616913261015L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "Id_Usuario")
	private Long idUsuario;

	@Column(name = "Nombre_Usuario")
	private String username;

	@Column(name = "Fraseword")
	private String password;

	@Column(name = "Id_Provincia")
	private Long IdProvincia;

	@Column(name = "Cedula")
	private Integer cedula;

	@Column(name = "Nombre")
	private String nombre;

	@Column(name = "Apellido")
	private String apellido;

	@Column(name = "Celular")
	private String celular;

	@Column(name = "Direccion")
	private String direccion;

	@Column(name = "Tel")
	private String tel;

	@Column(name = "Correo")
	private String Correo;
	
	//Relaciones
	@OneToMany
	@JoinColumn(name="Id_Usuario")
	private Set<Rol> roles = new HashSet<>();

	public Usuario() {
		// Constructor Vacio
	}

	public Usuario(Long idUsuario) {
		super();
		this.idUsuario = idUsuario;
	}
	

	public Usuario(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	

	public Usuario(Long idUsuario, String username, String password, Long idProvincia, Integer cedula, String nombre,
			String apellido, String celular, String direccion, String tel, String correo, Set<Rol> roles) {
		super();
		this.idUsuario = idUsuario;
		this.username = username;
		this.password = password;
		IdProvincia = idProvincia;
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.celular = celular;
		this.direccion = direccion;
		this.tel = tel;
		Correo = correo;
		this.roles = roles;
	}

	// Getters And Setters

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
	
	public Set<Rol> getRoles() {
		return roles;
	}

	public void setRoles(Set<Rol> roles) {
		this.roles = roles;
	}

	@Override
	public int hashCode() {
		return Objects.hash(Correo, IdProvincia, apellido, cedula, celular, direccion, idUsuario, nombre, password,
				roles, tel, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(Correo, other.Correo) && Objects.equals(IdProvincia, other.IdProvincia)
				&& Objects.equals(apellido, other.apellido) && Objects.equals(cedula, other.cedula)
				&& Objects.equals(celular, other.celular) && Objects.equals(direccion, other.direccion)
				&& Objects.equals(idUsuario, other.idUsuario) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(password, other.password) && Objects.equals(roles, other.roles)
				&& Objects.equals(tel, other.tel) && Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario + ", username=" + username + ", password=" + password
				+ ", IdProvincia=" + IdProvincia + ", cedula=" + cedula + ", nombre=" + nombre + ", apellido="
				+ apellido + ", celular=" + celular + ", direccion=" + direccion + ", tel=" + tel + ", Correo=" + Correo
				+ ", roles=" + roles + "]";
	}

}
