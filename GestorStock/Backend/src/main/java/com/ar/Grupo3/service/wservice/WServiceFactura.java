package com.ar.Grupo3.service.wservice;

import java.io.Serializable;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ar.Grupo3.data.factory.FabricaDAO;
import com.ar.Grupo3.data.objects.interfaces.DaoFacturaIntf;
import com.ar.Grupo3.model.Factura;
import com.ar.Grupo3.viewmodel.FacturaModel;

@RestController
@RequestMapping({ "/gestor" })
public class WServiceFactura implements Serializable {

	private static final long serialVersionUID = -1810321922583379449L;
	
	@Autowired
	private DaoFacturaIntf servicioFactura = FabricaDAO.obtenerFactura();

	/*
	 * ----------------------------------------------------------------------------
	 * Factura
	 * ----------------------------------------------------------------------------
	 */
	@GetMapping(path = "/factura")
	public List<FacturaModel> mostrarFactura() {
		List<FacturaModel> listaFactura = listar();
		return listaFactura;
	}

	@GetMapping(path = "/factura/{id}")
	public FacturaModel buscarFactura(@PathVariable("id") Long id) {
		return buscarPorId(id);
	}

	@PostMapping(path = "/factura")
	public Factura AgregarFactura(@RequestBody Factura Factura) {
		return agregamos(Factura);
	}

	@PutMapping(path = "/factura/{id}")
	public ResponseEntity<Factura> modificarFactura(@PathVariable("id") Long id,
			@RequestBody Factura factura) {
		try {
			if (factura != null) {
				modificamos(factura, id);
				return new ResponseEntity<Factura>(HttpStatus.OK);
			} else {
				throw new Exception("El objeto a modificar esta vacio");
			}
		} catch (Exception e) {
			return new ResponseEntity<Factura>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping(path = "/factura/{id}")
	public ResponseEntity<Factura> eliminarFactura(@PathVariable("id") Long id) {
		int var = eliminar(id);
		try {
			if (var != 0) {
				return new ResponseEntity<Factura>(HttpStatus.OK);
			} else {
				throw new Exception("El identificador del abono eliminar esta vacio");
			}
		} catch (Exception e) {
			return new ResponseEntity<Factura>(HttpStatus.NOT_FOUND);
		}
	}

	// __________________________________________________________________________________
	// Logica
	// __________________________________________________________________________________

	// Buscamos todos los objetos Factura
	public List<FacturaModel> listar() {
		return servicioFactura.SelectTodos();
	}

	// Buscamos un objeto Factura por ID
	public FacturaModel buscarPorId(Long id) {
		return servicioFactura.selectPorId(id);
	}
	
	// Buscamos un objeto FacturaModel por ID
	public Factura buscar(Long id) {
		return servicioFactura.buscar(id);
	}

	// Agregamos un objeto Factura por ID
	public Factura agregamos(Factura Factura) {
		servicioFactura.agregar(Factura);
		return Factura;
	}

	// modificamos el objeto Factura
	public void modificamos(Factura FacturaModificado, Long id) {
		// Verificamos que no nos llegue nulo el id
		if (id != null) {
			Factura busca = servicioFactura.buscar(id);
			// Ahora si modificamos la Factura
			if (busca != null) {
				servicioFactura.modificar(FacturaModificado);
			}
		}
	}

	// Eliminamos el objeto Factura
	public Integer eliminar(Long id) {
		Integer aux = 1;
		// nos fijamos que no nos llegue nulo
		try {
			if (id != null) {
				Factura elimina = new Factura(id);
				// eliminar el objeto Factura
				if (elimina != null) {
					servicioFactura.borrar(elimina);
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
