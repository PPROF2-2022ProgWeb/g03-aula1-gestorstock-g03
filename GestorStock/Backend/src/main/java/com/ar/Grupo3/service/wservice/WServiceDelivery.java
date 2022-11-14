package com.ar.Grupo3.service.wservice;

import java.io.Serializable;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ar.Grupo3.data.factory.FabricaDAO;
import com.ar.Grupo3.data.objects.interfaces.DaoDeliveryIntf;
import com.ar.Grupo3.model.Delivery;
import com.ar.Grupo3.viewmodel.DeliveryModel;

@RestController
@RequestMapping({"/gestor"})
@CrossOrigin(origins = {"http://localhost:4200", "https://gestor-stock.netlify.app", "https://gestor-stock-desa.netlify.app"})
public class WServiceDelivery implements Serializable {

    private static final long serialVersionUID = 6532179770005820687L;

    @Autowired
    private DaoDeliveryIntf servicioDelivery = FabricaDAO.obtenerDelivery();

    /*
	 * ----------------------------------------------------------------------------
	 * Delivery
	 * ----------------------------------------------------------------------------
     */
    @GetMapping(path = "/delivery")
    public List<DeliveryModel> mostrarDelivery() {
        List<DeliveryModel> listaDelivery = listar();
        return listaDelivery;
    }

    @GetMapping(path = "/delivery/{id}")
    public DeliveryModel buscarDelivery(@PathVariable("id") Long id) {
        return buscarPorId(id);
    }

    @PostMapping(path = "/delivery")
    public Delivery AgregarDelivery(@RequestBody Delivery delivery) {
        return agregamos(delivery);
    }

    @PutMapping(path = "/delivery/{id}")
    public ResponseEntity<Delivery> modificarDelivery(@PathVariable("id") Long id, @RequestBody Delivery delivery) {
        try {
            if (delivery != null) {
                modificamos(delivery, id);
                return new ResponseEntity<Delivery>(HttpStatus.OK);
            } else {
                throw new Exception("El objeto a modificar esta vacio");
            }
        } catch (Exception e) {
            return new ResponseEntity<Delivery>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/delivery/{id}")
    public ResponseEntity<Delivery> eliminarDelivery(@PathVariable("id") Long id) {
        int var = eliminar(id);
        try {
            if (var != 0) {
                return new ResponseEntity<Delivery>(HttpStatus.OK);
            } else {
                throw new Exception("El identificador del abono eliminar esta vacio");
            }
        } catch (Exception e) {
            return new ResponseEntity<Delivery>(HttpStatus.NOT_FOUND);
        }
    }

    // __________________________________________________________________________________
    // Logica
    // __________________________________________________________________________________
    // Bucamos todos las Delivery
    public List<DeliveryModel> listar() {
        return servicioDelivery.SelectTodos();
    }

    // Buscamos un objeto DeliveryModel por ID
    public DeliveryModel buscarPorId(Long id) {
        return servicioDelivery.selectPorId(id);
    }

    // Buscamos un objeto Delivery por ID
    public Delivery buscar(Long id) {
        return servicioDelivery.buscar(id);
    }

    // Agregamos un por ID
    public Delivery agregamos(Delivery Delivery) {
        servicioDelivery.agregar(Delivery);
        return Delivery;
    }

    // modificamos el objeto Delivery
    public void modificamos(Delivery DeliveryModificado, Long id) {
        // Verificamos que no nos llegue nulo el id
        if (id != null) {
            Delivery busca = servicioDelivery.buscar(id);
            // Ahora si modificamos el objeto Delivery
            if (busca != null) {
                servicioDelivery.modificar(DeliveryModificado);
            }
        }
    }

    // Eliminamos el objeto Delivery
    public Integer eliminar(Long id) {
        Integer aux = 1;
        // nos fijamos que no nos llegue nulo
        try {
            if (id != null) {
                Delivery elimina = new Delivery(id);
                // eliminar el objeto Delivery
                if (elimina != null) {
                    servicioDelivery.borrar(elimina);
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
