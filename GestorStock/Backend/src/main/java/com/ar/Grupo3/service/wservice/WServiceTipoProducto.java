package com.ar.Grupo3.service.wservice;

import java.io.Serializable;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ar.Grupo3.data.factory.FabricaDAO;
import com.ar.Grupo3.data.objects.interfaces.DaoTipoProductoIntf;
import com.ar.Grupo3.model.TipoProducto;

@RestController
@RequestMapping({"/gestor"})
@CrossOrigin(origins = {"http://localhost:4200", "https://gestor-stock.netlify.app", "https://gestor-stock-desa.netlify.app"})
public class WServiceTipoProducto implements Serializable {

    private static final long serialVersionUID = -7496362548758074135L;

    @Autowired
    private DaoTipoProductoIntf servicioTipoProducto = FabricaDAO.obtenerTipoProducto();

    /*
	 * ----------------------------------------------------------------------------
	 * TipoProducto
	 * ----------------------------------------------------------------------------
     */
    @GetMapping(path = "/tipoProducto")
    public List<TipoProducto> mostrarTipoProducto() {
        List<TipoProducto> listaTipoProducto = listar();
        return listaTipoProducto;
    }

    @GetMapping(path = "/tipoProducto/{id}")
    public TipoProducto buscarTipoProducto(@PathVariable("id") Long id) {
        return buscarPorId(id);
    }

    @PostMapping(path = "/tipoProducto")
    public TipoProducto AgregarTipoProducto(@RequestBody TipoProducto TipoProducto) {
        return agregamos(TipoProducto);
    }

    @PutMapping(path = "/tipoProducto/{id}")
    public ResponseEntity<TipoProducto> modificarTipoProducto(@PathVariable("id") Long id, @RequestBody TipoProducto tipoProducto) {
        try {
            if (tipoProducto != null) {
                modificamos(tipoProducto, id);
                return new ResponseEntity<TipoProducto>(HttpStatus.OK);
            } else {
                throw new Exception("El objeto a modificar esta vacio");
            }
        } catch (Exception e) {
            return new ResponseEntity<TipoProducto>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/tipoProducto/{id}")
    public ResponseEntity<TipoProducto> eliminarTipoProducto(@PathVariable("id") Long id) {
        int var = eliminar(id);
        try {
            if (var != 0) {
                return new ResponseEntity<TipoProducto>(HttpStatus.OK);
            } else {
                throw new Exception("El identificador del abono eliminar esta vacio");
            }
        } catch (Exception e) {
            return new ResponseEntity<TipoProducto>(HttpStatus.NOT_FOUND);
        }
    }

    // __________________________________________________________________________________
    // Logica
    // __________________________________________________________________________________
    // Buscamos todos los objetos TipoProducto
    public List<TipoProducto> listar() {
        return servicioTipoProducto.SelectTodos();
    }

    // Buscamos un objeto TipoProducto por ID
    public TipoProducto buscarPorId(Long id) {
        return servicioTipoProducto.selectPorId(id);
    }

    // Agregamos un objeto TipoProducto por ID
    public TipoProducto agregamos(TipoProducto TipoProducto) {
        servicioTipoProducto.agregar(TipoProducto);
        return TipoProducto;
    }

    // modificamos el objeto TipoProducto
    public void modificamos(TipoProducto TipoProductoModificado, Long id) {
        // Verificamos que no nos llegue nulo el id
        if (id != null) {
            TipoProducto busca = servicioTipoProducto.selectPorId(id);
            // Ahora si modificamos la TipoProducto
            if (busca != null) {
                servicioTipoProducto.modificar(TipoProductoModificado);
            }
        }
    }

    // Eliminamos el objeto TipoProducto
    public Integer eliminar(Long id) {
        Integer aux = 1;
        // nos fijamos que no nos llegue nulo
        try {
            if (id != null) {
                TipoProducto elimina = new TipoProducto(id);
                // eliminar el objeto TipoProducto
                if (elimina != null) {
                    servicioTipoProducto.borrar(elimina);
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
