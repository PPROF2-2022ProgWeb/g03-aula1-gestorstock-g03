package com.ar.Grupo3.Security.Service;

import com.ar.Grupo3.Security.Entity.Rol;
import com.ar.Grupo3.Security.Enums.NombresRol;
import com.ar.Grupo3.Security.Repository.IRolRepository;

import java.util.List;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class RolService {

    @Autowired
    IRolRepository irolRepository;

    public List<Rol> getByRolNombre(NombresRol rolNombre) {
        return irolRepository.findByNombreRol(rolNombre);
    }

    public void save(Rol rol) {
        irolRepository.save(rol);
    }
}
