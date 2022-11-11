package com.ar.Grupo3.service.wservice;

import java.io.Serializable;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ar.Grupo3.data.factory.FabricaDAO;
import com.ar.Grupo3.data.objects.interfaces.DaoAbonoIntf;
import com.ar.Grupo3.model.Abono;
import com.ar.Grupo3.viewmodel.AbonoModel;

@RestController
@RequestMapping({"/gestor"})
public class WServiceAbono implements Serializable {

    private static final long serialVersionUID = -4583253543089169364L;

    @Autowired
    private DaoAbonoIntf servicioAbono = FabricaDAO.obtenerAbono();

    /*
	 * ----------------------------------------------------------------------------
	 * Abono
	 * ----------------------------------------------------------------------------
     */
    @GetMapping(path = "/abono")
    public List<AbonoModel> mostrarAbono() {
        List<AbonoModel> listaAbono = listar();
        return listaAbono;
    }

    @GetMapping(path = "/abono/{id}")
    public AbonoModel buscarAbono(@PathVariable("id") Long id) {
        return buscar(id);
    }

    @PostMapping(path = "/abono")
    public Abono AgregarAbono(@RequestBody Abono abono) {
        return agregamos(abono);
    }

    @PutMapping(path = "/abono/{id}")
    public ResponseEntity<Abono> modificarAbono(@PathVariable("id") Long id, @RequestBody Abono abono) {
        try {
            if (abono != null) {
                modificamos(abono, id);
                return new ResponseEntity<Abono>(HttpStatus.OK);
            } else {
                throw new Exception("El objeto a modificar esta vacio");
            }
        } catch (Exception e) {
            return new ResponseEntity<Abono>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/abono/{id}")
    public ResponseEntity<Abono> eliminarAbono(@PathVariable("id") Long id) {
        int var = eliminar(id);
        try {
            if (var != 0) {
                return new ResponseEntity<Abono>(HttpStatus.OK);
            } else {
                throw new Exception("El identificador del abono eliminar esta vacio");
            }
        } catch (Exception e) {
            return new ResponseEntity<Abono>(HttpStatus.NOT_FOUND);
        }
    }

    // __________________________________________________________________________________
    // Logica
    // __________________________________________________________________________________
    // Bucamos todos las Abono
    public List<AbonoModel> listar() {
        return servicioAbono.SelectTodos();
    }

    // Buscamos un Abono por ID
    public Abono buscarPorId(Long id) {
        return servicioAbono.buscar(id);
    }

    // Buscamos un AbonoModel por id
    public AbonoModel buscar(Long id) {
        return servicioAbono.selectPorId(id);
    }

    // Agregamos un por ID
    public Abono agregamos(Abono Abono) {
        servicioAbono.agregar(Abono);
        return Abono;
    }

    // modificamos Abono
    public void modificamos(Abono abonoModificado, Long id) {
        // Verificamos que no nos llegue nulo el id
        if (id != null) {
            Abono busca = servicioAbono.buscar(id);
            // Ahora si modificamos la Abono
            if (busca != null) {
                servicioAbono.modificar(abonoModificado);
            }
        }
    }

    // Eliminamos el Abono
    public Integer eliminar(Long id) {
        Integer aux = 1;
        // nos fijamos que no nos llegue nulo
        try {
            if (id != null) {
                Abono elimina = new Abono(id);
                // eliminar el Abono
                if (elimina != null) {
                    servicioAbono.borrar(elimina);
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
