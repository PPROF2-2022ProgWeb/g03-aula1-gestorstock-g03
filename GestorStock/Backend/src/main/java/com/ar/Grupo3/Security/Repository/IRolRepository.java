/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ar.Grupo3.Security.Repository;

import com.ar.Grupo3.Security.Entity.Rol;
import com.ar.Grupo3.Security.Enums.NombresRol;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Long>{
    List<Rol> findByNombreRol(NombresRol rolNombre);
}
