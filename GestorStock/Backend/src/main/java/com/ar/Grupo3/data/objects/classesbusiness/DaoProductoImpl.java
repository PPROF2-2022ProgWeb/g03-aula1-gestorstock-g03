package com.ar.Grupo3.data.objects.classesbusiness;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.Grupo3.data.objects.interfaces.DaoProductoIntf;
import com.ar.Grupo3.data.objects.repositorio.*;
import com.ar.Grupo3.model.*;
import com.ar.Grupo3.util.ConvertidorFecha;
import com.ar.Grupo3.util.MensajesObjetos;
import com.ar.Grupo3.viewmodel.ProductoModel;

@Service
public class DaoProductoImpl implements Serializable, DaoProductoIntf {

	private static final long serialVersionUID = -4261968389037389395L;

	@Autowired
	private ProductoRepositorio dao;

	@Autowired
	private TipoProductoRepositorio tipoProducto;

	@Override
	public Producto buscar(Long id) {

		Producto producto = null;
		try {
			Optional<Producto> aux = dao.findById(id);
			if (aux.isEmpty()) {
				throw new Exception("El producto que busca NO EXISTE");
			} else {
				producto = aux.get();
			}
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}

		return producto;
	}
	
	@Override
	public ProductoModel selectPorId(Long id) {

		ProductoModel producto = new ProductoModel();
		try {
			Optional<Producto> aux = dao.findById(id);
			if (aux.isEmpty()) {
				throw new Exception("El producto que busca NO EXISTE");
			} else {
				Producto obj = aux.get();
				
				TipoProducto tp = buscarTipoProducto(obj);
				
				// si existe le asignamos el nombre si no un nombre predeterminado
				producto.setTipoProd( (aux!= null) ? tp.getNombreTipoProd() : MensajesObjetos.TIPO_PRODUCTO_NO_RELACIONADO);
			
				// Completamos el objeto que mostraremos a la vista.
				producto.setFechaIng(obj.getFechaIng());
				producto.setIdProducto(obj.getIdProducto());
				producto.setIdTipoProd(obj.getIdTipoProd());
				producto.setCantidad(obj.getCantidad());
				producto.setBarCode(obj.getBarCode());
				producto.setClave(obj.getClave());
				producto.setImageURL(obj.getImageURL());
				producto.setCosto(obj.getCosto());
				producto.setNombreProducto(obj.getNombreProducto());
				producto.setValor(obj.getValor());

				if (producto.getIdTipoProd() != null) {
					producto.setTipoProd(buscarTipoProducto(obj).getNombreTipoProd());
				} else {
					producto.setTipoProd(MensajesObjetos.TIPO_PRODUCTO_NO_RELACIONADO);
				}
			}
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}

		return producto;
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
	public void agregar(Producto p) {
		try {
			if (p.getNombreProducto().isEmpty()) {
				throw new Exception("El Producto que va a registrar no tiene Nombre");
			}
			if (p.getFechaIng() == null) {
				p.setFechaIng(ConvertidorFecha.FechaActual());
			}
			
			dao.save(p);

		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}
	}

	@Override
	public void modificar(Producto p) {
		try {
			if (p.getIdProducto() == null) {
				throw new Exception("El Producto que va a registrar no tiene identificador de la mismo");
			}
			if (p.getNombreProducto().isEmpty()) {
				throw new Exception("El Producto que va a registrar no tiene Nombre");
			}
			if (p.getFechaIng() == null) {
				p.setFechaIng(ConvertidorFecha.FechaActual());
			}
			dao.save(p);

		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}
	}

	@Override
	public void borrar(Producto p) {
		try {
			if (p.getIdProducto() == null) {
				throw new Exception("El producto a eliminar no tiene identificador");
			}
			// Revisamos que El Producto exista
			p = buscar(p.getIdProducto());
			if (p != null) {
				dao.delete(p);
			}

		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock ");
		}
	}

	@Override
	public List<ProductoModel> SelectTodos() {
		List<ProductoModel> list = null;
		try {
			List<Producto> productos = dao.findAll();

			// Si la lista no esta vacia
			if (!productos.isEmpty()) {

				list = new LinkedList<>();

				// Recorremos la lista
				for (Producto producto : productos) {
					// Buscamos id por id
					TipoProducto aux = buscarTipoProducto(producto);

					//Este es el nuevo objeto que se mostrar para la vista
					ProductoModel nuevoObjeto = new ProductoModel();
					
					nuevoObjeto.setFechaIng(producto.getFechaIng());
					nuevoObjeto.setIdProducto(producto.getIdProducto());
					nuevoObjeto.setIdTipoProd(producto.getIdTipoProd());
					nuevoObjeto.setCantidad(producto.getCantidad());
					nuevoObjeto.setBarCode(producto.getBarCode());
					nuevoObjeto.setClave(producto.getClave());
					nuevoObjeto.setImageURL(producto.getImageURL());
					nuevoObjeto.setCosto(producto.getCosto());
					nuevoObjeto.setNombreProducto(producto.getNombreProducto());
					nuevoObjeto.setValor(producto.getValor());
					
					// si existe le asignamos el nombre si no un nombre predeterminado
					if (aux != null) {
						nuevoObjeto.setTipoProd(aux.getNombreTipoProd());
					} else {
						nuevoObjeto.setTipoProd(MensajesObjetos.TIPO_PRODUCTO_NO_RELACIONADO);
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
	public boolean existeTipoProducto(Producto objeto) {
		boolean encontrado = false;
		// Nos fijamos que los datos existan
		if (objeto != null) {
			if (buscarTipoProducto(objeto) != null) {
				encontrado = true;
			}
		}
		return encontrado;
	}

	public TipoProducto buscarTipoProducto(Producto objeto) {
		TipoProducto fact = null;
		try {
			Optional<TipoProducto> faBusqueda = tipoProducto.findById(objeto.getIdTipoProd());

			if (faBusqueda.get().getIdTipoProducto().equals(objeto.getIdTipoProd())) {
				fact = faBusqueda.get();
			} else {
				throw new Exception("El producto que va a registrar no tiene Tipo de Producto creado");
			}
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}

		return fact;
	}

}
