package com.ar.Grupo3.service.wservice;

import java.io.Serializable;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ar.Grupo3.data.factory.FabricaDAO;
import com.ar.Grupo3.data.objects.interfaces.DaoProvinciaIntf;
import com.ar.Grupo3.model.Provincia;
import com.ar.Grupo3.viewmodel.ProvinciaModel;

@RestController
@RequestMapping({"/gestor"})
public class WServiceProvincia implements Serializable {

    private static final long serialVersionUID = -8334956126366208648L;

    @Autowired
    private DaoProvinciaIntf servicioProvincia = FabricaDAO.obtenerProvincia();

    /*
	 * ----------------------------------------------------------------------------
	 * Provincia
	 * ----------------------------------------------------------------------------
     */
    @GetMapping(path = "/provincia")
    public List<ProvinciaModel> mostrarProvincia() {
        List<ProvinciaModel> listaProvincia = listar();
        return listaProvincia;
    }

    @GetMapping(path = "/provincia/{id}")
    public ProvinciaModel buscarProvincia(@PathVariable("id") Long id) {
        return buscarPorId(id);
    }

    @PostMapping(path = "/provincia")
    public Provincia AgregarProvincia(@RequestBody Provincia Provincia) {
        return agregamos(Provincia);
    }

    @PutMapping(path = "/provincia/{id}")
    public ResponseEntity<Provincia> modificarProvincia(@PathVariable("id") Long id, @RequestBody Provincia provincia) {
        try {
            if (provincia != null) {
                modificamos(provincia, id);
                return new ResponseEntity<Provincia>(HttpStatus.OK);
            } else {
                throw new Exception("El objeto a modificar esta vacio");
            }
        } catch (Exception e) {
            return new ResponseEntity<Provincia>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/provincia/{id}")
    public ResponseEntity<Provincia> eliminarProvincia(@PathVariable("id") Long id) {
        int var = eliminar(id);
        try {
            if (var != 0) {
                return new ResponseEntity<Provincia>(HttpStatus.OK);
            } else {
                throw new Exception("El identificador del abono eliminar esta vacio");
            }
        } catch (Exception e) {
            return new ResponseEntity<Provincia>(HttpStatus.NOT_FOUND);
        }
    }

    // __________________________________________________________________________________
    // Logica
    // __________________________________________________________________________________
    // Bucamos todos las Provinciaes
    public List<ProvinciaModel> listar() {
        return servicioProvincia.SelectTodos();
    }

    // Buscamos un Provincia por ID
    public Provincia buscar(Long id) {
        return servicioProvincia.buscar(id);
    }

    // Buscamos un ProvinciaModel por Id
    public ProvinciaModel buscarPorId(Long id) {
        return servicioProvincia.selectPorId(id);
    }

    // Agregamos un por ID
    public Provincia agregamos(Provincia Provincia) {
        servicioProvincia.agregar(Provincia);
        return Provincia;
    }

    // modificamos el objeto Provincia
    public void modificamos(Provincia ProvinciaModificado, Long id) {
        // Verificamos que no nos llegue nulo el id
        if (id != null) {
            Provincia busca = servicioProvincia.buscar(id);
            // Ahora si modificamos la Provincia
            if (busca != null) {
                servicioProvincia.modificar(ProvinciaModificado);
            }
        }
    }

    // Eliminamos la Provincia
    public Integer eliminar(Long id) {
        Integer aux = 1;
        // nos fijamos que no nos llegue nulo
        try {
            if (id != null) {
                Provincia elimina = new Provincia(id);
                // eliminar la Provincia
                if (elimina != null) {
                    servicioProvincia.borrar(elimina);
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
