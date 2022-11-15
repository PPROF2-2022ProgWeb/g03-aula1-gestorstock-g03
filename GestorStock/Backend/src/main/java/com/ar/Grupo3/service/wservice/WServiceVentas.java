package com.ar.Grupo3.service.wservice;

import java.io.Serializable;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ar.Grupo3.data.factory.FabricaDAO;
import com.ar.Grupo3.data.objects.interfaces.DaoVentasIntf;
import com.ar.Grupo3.model.Ventas;
import com.ar.Grupo3.viewmodel.VentasModel;

@RestController
@RequestMapping({"/gestor"})
@CrossOrigin(origins = {"http://localhost:4200", "https://gestor-stock.netlify.app", "https://gestor-stock-desa.netlify.app"})
public class WServiceVentas implements Serializable {

    private static final long serialVersionUID = 368877546601868070L;

    @Autowired
    private DaoVentasIntf servicioVentas = FabricaDAO.obtenerVentas();

    /*
	 * ----------------------------------------------------------------------------
	 * Ventas
	 * ----------------------------------------------------------------------------
     */
    @GetMapping(path = "/ventas")
    public List<VentasModel> mostrarVentas() {
        List<VentasModel> listaVentas = listar();
        return listaVentas;
    }

    @GetMapping(path = "/ventas/{id}")
    public VentasModel buscarVentas(@PathVariable("id") Long id) {
        return buscarPorId(id);
    }

    @PostMapping(path = "/ventas")
    public Ventas AgregarVentas(@RequestBody Ventas Ventas) {
        return agregamos(Ventas);
    }

    @PutMapping(path = "/ventas/{id}")
    public ResponseEntity<Ventas> modificarVentas(@PathVariable("id") Long id, @RequestBody Ventas ventas) {
        try {
            if (ventas != null) {
                modificamos(ventas, id);
                return new ResponseEntity<Ventas>(HttpStatus.OK);
            } else {
                throw new Exception("El objeto a modificar esta vacio");
            }
        } catch (Exception e) {
            return new ResponseEntity<Ventas>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/ventas/{id}")
    public ResponseEntity<Ventas> eliminarVentas(@PathVariable("id") Long id) {
        int var = eliminar(id);
        try {
            if (var != 0) {
                return new ResponseEntity<Ventas>(HttpStatus.OK);
            } else {
                throw new Exception("El identificador del abono eliminar esta vacio");
            }
        } catch (Exception e) {
            return new ResponseEntity<Ventas>(HttpStatus.NOT_FOUND);
        }
    }

    // __________________________________________________________________________________
    // Logica
    // __________________________________________________________________________________
    // Buscamos todos los objetos Ventas
    public List<VentasModel> listar() {
        return servicioVentas.SelectTodos();
    }

    // Buscamos un objeto Ventas por ID
    public VentasModel buscarPorId(Long id) {
        return servicioVentas.selectPorId(id);
    }

    // Buscamos un objeto Ventas por ID
    public Ventas buscar(Long id) {
        return servicioVentas.buscar(id);
    }

    // Agregamos un objeto Ventas por ID
    public Ventas agregamos(Ventas Ventas) {
        servicioVentas.agregar(Ventas);
        return Ventas;
    }

    // modificamos el objeto Ventas
    public void modificamos(Ventas VentasModificado, Long id) {
        // Verificamos que no nos llegue nulo el id
        if (id != null) {
            Ventas busca = servicioVentas.buscar(id);
            // Ahora si modificamos la Ventas
            if (busca != null) {
                servicioVentas.modificar(VentasModificado);
            }
        }
    }

    // Eliminamos el objeto Ventas
    public Integer eliminar(Long id) {
        Integer aux = 1;
        // nos fijamos que no nos llegue nulo
        try {
            if (id != null) {
                Ventas elimina = new Ventas(id);
                // eliminar el objeto Ventas
                if (elimina != null) {
                    servicioVentas.borrar(elimina);
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
