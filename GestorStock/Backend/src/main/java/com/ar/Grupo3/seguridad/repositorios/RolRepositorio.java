package com.ar.Grupo3.seguridad.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ar.Grupo3.seguridad.Enums.NombresRol;
import com.ar.Grupo3.seguridad.entity.Rol;

@Repository
public interface RolRepositorio extends JpaRepository<Rol, Long> {
	List<Rol> findByNombreRol(NombresRol nombreRol);
}
