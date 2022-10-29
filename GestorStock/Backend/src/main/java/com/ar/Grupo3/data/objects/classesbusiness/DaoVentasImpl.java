package com.ar.Grupo3.data.objects.classesbusiness;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.Grupo3.data.objects.interfaces.DaoVentasIntf;
import com.ar.Grupo3.data.objects.repositorio.*;
import com.ar.Grupo3.model.*;
import com.ar.Grupo3.util.MensajesObjetos;
import com.ar.Grupo3.viewmodel.VentasModel;

@Service
public class DaoVentasImpl implements Serializable, DaoVentasIntf {

	private static final long serialVersionUID = -1781920215637427233L;

	@Autowired
	private VentasRepositorio dao;

	@Autowired
	private ProductoRepositorio producto;

	@Autowired
	private FacturaRepositorio factura;

	@Override
	public Ventas buscar(Long id) {

		Ventas ventas = null;
		try {
			Optional<Ventas> aux = dao.findById(id);
			if (aux.isEmpty()) {
				throw new Exception("El ventas que busca NO EXISTE");
			} else {
				ventas = aux.get();
			}
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}

		return ventas;
	}
	
	@Override
	public VentasModel selectPorId(Long id) {

		VentasModel ventas = new VentasModel();
		try {
			Optional<Ventas> aux = dao.findById(id);
			if (aux.isEmpty()) {
				throw new Exception("El ventas que busca NO EXISTE");
			} else {
				Ventas obj = aux.get();

				Factura fac = buscarFactura(obj);
				Producto pro = buscarProducto(obj);
				// Completamos el objeto que mostraremos a la vista.
				ventas.setEstado(obj.getEstado());
				ventas.setIdVenta(obj.getIdVenta());
				ventas.setIdFactura(obj.getIdFactura());
				ventas.setIdProducto(obj.getIdProducto());
				ventas.setCantidad(obj.getCantidad());
				ventas.setDescuento(obj.getDescuento());
				ventas.setNombreVenta(obj.getNombreVenta());

				// si existe le asignamos el nombre si no un nombre predeterminado
				if (fac != null) {
					ventas.setNombreFactura(fac.getNombreFactura());
				} else {
					ventas.setNombreFactura(MensajesObjetos.FACTURA_NO_RELACIONADA);
				}

				if (pro != null) {
					ventas.setNombreProducto(pro.getNombreProducto());
				} else {
					ventas.setNombreProducto(MensajesObjetos.PRODUCTO_NO_RELACIONADO);
				}
			}
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}

		return ventas;
	}

	@Override
	public Long contarTodos() {
		long cantidad = 0;

		try {
			cantidad = dao.count();
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}

		return cantidad;
	}

	@Override
	public void agregar(Ventas p) {
		try {
			if (p.getNombreVenta().isEmpty()) {
				throw new Exception("El Objeto 'Ventas' que va a registrar no tiene nombre");
			}
			dao.save(p);
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}
	}

	@Override
	public void modificar(Ventas p) {
		try {
			if (p.getIdVenta() == null) {
				throw new Exception("la Venta que va a registrar no tiene identificador del mismo");
			}
			if (p.getNombreVenta().isEmpty()) {
				throw new Exception("El Objeto 'Ventas' que va a registrar no tiene nombre");
			}

			dao.save(p);

		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}

	}

	@Override
	public void borrar(Ventas p) {
		try {
			if (p.getIdVenta() == null) {
				throw new Exception("El ventas a eliminar no tiene identificador");
			}
			// Revisamos que El Ventas exista
			p = buscar(p.getIdVenta());
			if (p != null) {
				dao.delete(p);
			}

		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock ");
		}
	}

	@Override
	public List<VentasModel> SelectTodos() {
		List<VentasModel> list = null;
		try {
			List<Ventas> ventass = dao.findAll();

			// Si la lista no esta vacia
			if (!ventass.isEmpty()) {

				list = new LinkedList<>();

				// Recorremos la lista
				for (Ventas ventas : ventass) {
					// Buscamos id por id
					Factura fac = buscarFactura(ventas);
					
					Producto pro = buscarProducto(ventas);

					//Este es el nuevo objeto que se mostrar para la vista
					VentasModel nuevoObjeto = new VentasModel();
					
					nuevoObjeto.setEstado(ventas.getEstado());
					nuevoObjeto.setIdVenta(ventas.getIdVenta());
					nuevoObjeto.setIdFactura(ventas.getIdFactura());
					nuevoObjeto.setIdProducto(ventas.getIdProducto());
					nuevoObjeto.setCantidad(ventas.getCantidad());
					nuevoObjeto.setDescuento(ventas.getDescuento());
					nuevoObjeto.setNombreVenta(ventas.getNombreVenta());

					// si existe le asignamos el nombre si no un nombre predeterminado
					if (fac != null) {
						nuevoObjeto.setNombreFactura(fac.getNombreFactura());
					} else {
						nuevoObjeto.setNombreFactura(MensajesObjetos.FACTURA_NO_RELACIONADA);
					}

					if (pro != null) {
						nuevoObjeto.setNombreProducto(pro.getNombreProducto());
					} else {
						nuevoObjeto.setNombreProducto(MensajesObjetos.PRODUCTO_NO_RELACIONADO);
					}
					
					list.add(nuevoObjeto);
				}
			}
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}
		return list;
	}

	// Metodos unicos
	public boolean existeProducto(Ventas objeto) {
		boolean encontrado = false;
		// Nos fijamos que los datos existan
		if (objeto != null) {
			if (buscarFactura(objeto) != null) {
				encontrado = true;
			}
		}
		return encontrado;
	}

	public boolean existeFactura(Ventas objeto) {
		boolean encontrado = false;
		// Nos fijamos que los datos existan
		if (objeto != null) {
			if (buscarFactura(objeto) != null) {
				encontrado = true;
			}
		}
		return encontrado;
	}
	
	public Producto buscarProducto(Ventas objeto) {
		Producto fact = null;
		try {
			Optional<Producto> faBusqueda = producto.findById(objeto.getIdProducto());

			if (faBusqueda.get().getIdProducto().equals(objeto.getIdProducto())) {
				fact = faBusqueda.get();
			} else {
				throw new Exception("El ventas que va a registrar no tiene Producto creado");
			}
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}

		return fact;
	}

	public Factura buscarFactura(Ventas objeto) {
		Factura fact = null;
		try {
			Optional<Factura> faBusqueda = factura.findById(objeto.getIdFactura());
			
			if (faBusqueda.get().getIdFactura().equals(objeto.getIdFactura())) {
				fact = faBusqueda.get();
			} else {
				throw new Exception("El ventas que va a registrar no tiene Factura creada");
			}
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
			+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}
		
		return fact;
	}
	
}
