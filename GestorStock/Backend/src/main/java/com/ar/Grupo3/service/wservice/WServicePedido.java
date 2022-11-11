package com.ar.Grupo3.service.wservice;

import java.io.Serializable;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ar.Grupo3.data.factory.FabricaDAO;
import com.ar.Grupo3.data.objects.interfaces.DaoPedidoIntf;
import com.ar.Grupo3.model.Pedido;
import com.ar.Grupo3.viewmodel.PedidoModel;

@RestController
@RequestMapping({"/gestor"})
public class WServicePedido implements Serializable {

    private static final long serialVersionUID = 3467511412356719130L;

    @Autowired
    private DaoPedidoIntf servicioPedido = FabricaDAO.obtenerPedido();

    /*
	 * ----------------------------------------------------------------------------
	 * Pedido
	 * ----------------------------------------------------------------------------
     */
    @GetMapping(path = "/pedido")
    public List<PedidoModel> mostrarPedido() {
        List<PedidoModel> listaPedido = listar();
        return listaPedido;
    }

    @GetMapping(path = "/pedido/{id}")
    public PedidoModel buscarPedido(@PathVariable("id") Long id) {
        return buscarPorId(id);
    }

    @PostMapping(path = "/pedido")
    public Pedido AgregarPedido(@RequestBody Pedido Pedido) {
        return agregamos(Pedido);
    }

    @PutMapping(path = "/pedido/{id}")
    public ResponseEntity<Pedido> modificarPedido(@PathVariable("id") Long id,
            @RequestBody Pedido pedido) {
        try {
            if (pedido != null) {
                modificamos(pedido, id);
                return new ResponseEntity<Pedido>(HttpStatus.OK);
            } else {
                throw new Exception("El objeto a modificar esta vacio");
            }
        } catch (Exception e) {
            return new ResponseEntity<Pedido>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/pedido/{id}")
    public ResponseEntity<Pedido> eliminarPedido(@PathVariable("id") Long id) {
        int var = eliminar(id);
        try {
            if (var != 0) {
                return new ResponseEntity<Pedido>(HttpStatus.OK);
            } else {
                throw new Exception("El identificador del abono eliminar esta vacio");
            }
        } catch (Exception e) {
            return new ResponseEntity<Pedido>(HttpStatus.NOT_FOUND);
        }
    }

    // __________________________________________________________________________________
    // Logica
    // __________________________________________________________________________________
    // Buscamos todos los objetos Pedido
    public List<PedidoModel> listar() {
        return servicioPedido.SelectTodos();
    }

    // Buscamos un objeto PedidoModel por ID
    public PedidoModel buscarPorId(Long id) {
        return servicioPedido.selectPorId(id);
    }

    // Buscamos un objeto Pedido por ID
    public Pedido buscar(Long id) {
        return servicioPedido.buscar(id);
    }

    // Agregamos un objeto Pedido por ID
    public Pedido agregamos(Pedido Pedido) {
        servicioPedido.agregar(Pedido);
        return Pedido;
    }

    // modificamos el objeto Pedido
    public void modificamos(Pedido PedidoModificado, Long id) {
        // Verificamos que no nos llegue nulo el id
        if (id != null) {
            Pedido busca = servicioPedido.buscar(id);
            // Ahora si modificamos la Pedido
            if (busca != null) {
                servicioPedido.modificar(PedidoModificado);
            }
        }
    }

    // Eliminamos el objeto Pedido
    public Integer eliminar(Long id) {
        Integer aux = 1;
        // nos fijamos que no nos llegue nulo
        try {
            if (id != null) {
                Pedido elimina = new Pedido(id);
                // eliminar el objeto Pedido
                if (elimina != null) {
                    servicioPedido.borrar(elimina);
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
