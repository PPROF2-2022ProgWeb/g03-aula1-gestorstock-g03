package com.ar.Grupo3.service.wservice;

import java.io.Serializable;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ar.Grupo3.data.factory.FabricaDAO;
import com.ar.Grupo3.data.objects.interfaces.DaoProductoIntf;
import com.ar.Grupo3.model.Producto;
import com.ar.Grupo3.viewmodel.ProductoModel;

@RestController
@RequestMapping({ "/gestor" })
public class WServiceProducto implements Serializable {

	private static final long serialVersionUID = -7962830043869265637L;

	@Autowired
	private DaoProductoIntf servicioProducto = FabricaDAO.obtenerProducto();

	/*
	 * ----------------------------------------------------------------------------
	 * Producto
	 * ----------------------------------------------------------------------------
	 */
	@GetMapping(path = "/producto")
	public List<ProductoModel> mostrarProducto() {
		List<ProductoModel> listaProducto = listar();
		return listaProducto;
	}

	@GetMapping(path = "/producto/{id}")
	public ProductoModel buscarProducto(@PathVariable("id") Long id) {
		return buscarPorId(id);
	}

	@PostMapping(path = "/producto")
	public Producto AgregarProducto(@RequestBody Producto Producto) {
		return agregamos(Producto);
	}

	@PutMapping(path = "/producto/{id}")
	public ResponseEntity<Producto> modificarProducto(@PathVariable("id") Long id, @RequestBody Producto producto) {
		try {
			if (producto != null) {
				modificamos(producto, id);
				return new ResponseEntity<Producto>(HttpStatus.OK);
			} else {
				throw new Exception("El objeto a modificar esta vacio");
			}
		} catch (Exception e) {
			return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping(path = "/producto/{id}")
	public ResponseEntity<Producto> eliminarProducto(@PathVariable("id") Long id) {
		int var = eliminar(id);
		try {
			if (var != 0) {
				return new ResponseEntity<Producto>(HttpStatus.OK);
			} else {
				throw new Exception("El identificador del abono eliminar esta vacio");
			}
		} catch (Exception e) {
			return new ResponseEntity<Producto>(HttpStatus.NOT_FOUND);
		}
	}

	// __________________________________________________________________________________
	// Logica
	// __________________________________________________________________________________

	// Buscamos todos los objetos Producto
	public List<ProductoModel> listar() {
		return servicioProducto.SelectTodos();
	}

	// Buscamos un objeto ProductoModel por ID
	public ProductoModel buscarPorId(Long id) {
		return servicioProducto.selectPorId(id);
	}

	// Buscamos un objeto Producto por ID
	public Producto buscar(Long id) {
		return servicioProducto.buscar(id);
	}
	
	// Agregamos un objeto Producto por ID
	public Producto agregamos(Producto Producto) {
		servicioProducto.agregar(Producto);
		return Producto;
	}

	// modificamos el objeto Producto
	public void modificamos(Producto ProductoModificado, Long id) {
		// Verificamos que no nos llegue nulo el id
		if (id != null) {
			Producto busca = servicioProducto.buscar(id);
			// Ahora si modificamos la Producto
			if (busca != null) {
				servicioProducto.modificar(ProductoModificado);
			}
		}
	}

	// Eliminamos el objeto Producto
	public Integer eliminar(Long id) {
		Integer aux = 1;
		// nos fijamos que no nos llegue nulo
		try {
			if (id != null) {
				Producto elimina = new Producto(id);
				// eliminar el objeto Producto
				if (elimina != null) {
					servicioProducto.borrar(elimina);
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
