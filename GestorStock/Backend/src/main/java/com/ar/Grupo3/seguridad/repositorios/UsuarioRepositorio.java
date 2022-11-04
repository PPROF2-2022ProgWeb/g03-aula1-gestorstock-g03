package com.ar.Grupo3.seguridad.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ar.Grupo3.seguridad.entity.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {

	//Metodo para encontrar nombre de usuario
	Usuario findByUsername(String nombre);
	
}
