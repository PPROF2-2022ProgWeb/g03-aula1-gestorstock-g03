package com.ar.Grupo3.service.wservice;

import java.io.Serializable;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ar.Grupo3.Security.Entity.Rol;
import com.ar.Grupo3.data.factory.FabricaDAO;
import com.ar.Grupo3.data.objects.interfaces.DaoRolIntf;
import com.ar.Grupo3.viewmodel.RolModel;

@RestController
@RequestMapping({"/gestor"})
public class WServiceRol implements Serializable {

    private static final long serialVersionUID = -2113872330758510906L;

    @Autowired
    private DaoRolIntf servicioRol = FabricaDAO.obtenerRol();

    /*
	 * ----------------------------------------------------------------------------
	 * Rol
	 * ----------------------------------------------------------------------------
     */
    @GetMapping(path = "/rol")
    public List<RolModel> mostrarRol() {
        List<RolModel> listaRol = listar();
        return listaRol;
    }

    @GetMapping(path = "/rol/{id}")
    public RolModel buscarRol(@PathVariable("id") Long id) {
        return buscar(id);
    }

    @PostMapping(path = "/rol")
    public Rol AgregarRol(@RequestBody Rol rol) {
        return agregamos(rol);
    }

    @PutMapping(path = "/rol/{id}")
    public ResponseEntity<Rol> modificarRol(@PathVariable("id") Long id, @RequestBody Rol rol) {
        try {
            if (rol != null) {
                modificamos(rol, id);
                return new ResponseEntity<Rol>(HttpStatus.OK);
            } else {
                throw new Exception("El objeto a modificar esta vacio");
            }
        } catch (Exception e) {
            return new ResponseEntity<Rol>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/rol/{id}")
    public ResponseEntity<Rol> eliminarRol(@PathVariable("id") Long id) {
        int var = eliminar(id);
        try {
            if (var != 0) {
                return new ResponseEntity<Rol>(HttpStatus.OK);
            } else {
                throw new Exception("El identificador del rol para eliminar esta vacio");
            }
        } catch (Exception e) {
            return new ResponseEntity<Rol>(HttpStatus.NOT_FOUND);
        }
    }

    // __________________________________________________________________________________
    // Logica
    // __________________________________________________________________________________
    // Bucamos todos las Rol
    public List<RolModel> listar() {
        return servicioRol.SelectTodos();
    }

    // Buscamos un Rol por ID
    public Rol buscarPorId(Long id) {
        return servicioRol.buscar(id);
    }

    // Buscamos un RolModel por id
    public RolModel buscar(Long id) {
        return servicioRol.selectPorId(id);
    }

    // Agregamos un por ID
    public Rol agregamos(Rol Rol) {
        servicioRol.agregar(Rol);
        return Rol;
    }

    // modificamos Rol
    public void modificamos(Rol rolModificado, Long id) {
        // Verificamos que no nos llegue nulo el id
        if (id != null) {
            Rol busca = servicioRol.buscar(id);
            // Ahora si modificamos la Rol
            if (busca != null) {
                servicioRol.modificar(rolModificado);
            }
        }
    }

    // Eliminamos el Rol
    public Integer eliminar(Long id) {
        Integer aux = 1;
        // nos fijamos que no nos llegue nulo
        try {
            if (id != null) {
                Rol elimina = new Rol(id);
                // eliminar el Rol
                if (elimina != null) {
                    servicioRol.borrar(elimina);
                    aux = 0;
                }
            }
        } catch (Exception e) {
            LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
                    + " } fin del error preguntar al Grupo 3 ==> GestorStock");
        }
        return aux;
    }

}
