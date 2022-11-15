package com.ar.Grupo3.service.wservice;

import java.io.Serializable;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ar.Grupo3.data.factory.FabricaDAO;
import com.ar.Grupo3.data.objects.interfaces.DaoServicioIntf;
import com.ar.Grupo3.model.Servicio;
import com.ar.Grupo3.viewmodel.ServicioModel;

@RestController
@RequestMapping({"/gestor"})
@CrossOrigin(origins = {"http://localhost:4200", "https://gestor-stock.netlify.app", "https://gestor-stock-desa.netlify.app"})
public class WServiceServicio implements Serializable {

    private static final long serialVersionUID = 6784654504573424831L;

    @Autowired
    private DaoServicioIntf servicioServicio = FabricaDAO.obtenerServicio();

    /*
	 * ----------------------------------------------------------------------------
	 * Servicio
	 * ----------------------------------------------------------------------------
     */
    @GetMapping(path = "/servicio")
    public List<ServicioModel> mostrarServicio() {
        List<ServicioModel> listaServicio = listar();
        return listaServicio;
    }

    @GetMapping(path = "/servicio/{id}")
    public ServicioModel buscarServicio(@PathVariable("id") Long id) {
        return buscarPorId(id);
    }

    @PostMapping(path = "/servicio")
    public Servicio AgregarServicio(@RequestBody Servicio Servicio) {
        return agregamos(Servicio);
    }

    @PutMapping(path = "/servicio/{id}")
    public ResponseEntity<Servicio> modificarServicio(@PathVariable("id") Long id, @RequestBody Servicio service) {
        try {
            if (service != null) {
                modificamos(service, id);
                return new ResponseEntity<Servicio>(HttpStatus.OK);
            } else {
                throw new Exception("El objeto a modificar esta vacio");
            }
        } catch (Exception e) {
            return new ResponseEntity<Servicio>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/servicio/{id}")
    public ResponseEntity<Servicio> eliminarServicio(@PathVariable("id") Long id) {
        int var = eliminar(id);
        try {
            if (var != 0) {
                return new ResponseEntity<Servicio>(HttpStatus.OK);
            } else {
                throw new Exception("El identificador del abono eliminar esta vacio");
            }
        } catch (Exception e) {
            return new ResponseEntity<Servicio>(HttpStatus.NOT_FOUND);
        }
    }

    // __________________________________________________________________________________
    // Logica
    // __________________________________________________________________________________
    // Buscamos todos los objetos Servicio
    public List<ServicioModel> listar() {
        return servicioServicio.SelectTodos();
    }

    // Buscamos un objeto Servicio por ID
    public ServicioModel buscarPorId(Long id) {
        return servicioServicio.selectPorId(id);
    }

    // Buscamos un objeto Servicio por ID
    public Servicio buscar(Long id) {
        return servicioServicio.buscar(id);
    }

    // Agregamos un objeto Servicio por ID
    public Servicio agregamos(Servicio Servicio) {
        servicioServicio.agregar(Servicio);
        return Servicio;
    }

    // modificamos el objeto Servicio
    public void modificamos(Servicio ServicioModificado, Long id) {
        // Verificamos que no nos llegue nulo el id
        if (id != null) {
            Servicio busca = servicioServicio.buscar(id);
            // Ahora si modificamos la Servicio
            if (busca != null) {
                servicioServicio.modificar(ServicioModificado);
            }
        }
    }

    // Eliminamos el objeto Servicio
    public Integer eliminar(Long id) {
        Integer aux = 1;
        // nos fijamos que no nos llegue nulo
        try {
            if (id != null) {
                Servicio elimina = new Servicio(id);
                // eliminar el objeto Servicio
                if (elimina != null) {
                    servicioServicio.borrar(elimina);
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
