package com.ar.Grupo3.data.objects.classesbusiness;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.Grupo3.data.objects.interfaces.DaoTipoProductoIntf;
import com.ar.Grupo3.data.objects.repositorio.*;
import com.ar.Grupo3.model.*;

@Service
public class DaoTipoProductoImpl implements Serializable, DaoTipoProductoIntf {

	private static final long serialVersionUID = -5820558651571290560L;
	
	@Autowired
	private TipoProductoRepositorio dao;
	
	@Override
	public TipoProducto selectPorId(Long id) {

		TipoProducto tipoProducto = null;
		try {
			Optional<TipoProducto> aux = dao.findById(id);
			if (aux.isEmpty()) {
				throw new Exception("El tipoProducto que busca NO EXISTE");
			} else {
				tipoProducto = aux.get();
			}
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}

		return tipoProducto;
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
	public void agregar(TipoProducto p) {
		try {
			if (p.getNombreTipoProd().isEmpty()) {
				throw new Exception("El Tipo de Producto que va a registrar no tiene Nombre");
			}
			dao.save(p);

		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}

	}

	@Override
	public void modificar(TipoProducto p) {
		try {
			if (p.getIdTipoProducto() == null) {
				throw new Exception("El Tipo de producto que va a registrar no tiene identificador del mismo");
			}
			
			dao.save(p);
			
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}

	}

	@Override
	public void borrar(TipoProducto p) {
		try {
			if (p.getIdTipoProducto() == null) {
				throw new Exception("El Tipo de producto que va a eliminar no tiene identificador");
			}
			// Revisamos que el pedido exista
			p = buscarTipoProducto(p);
			if (p != null) {
				dao.delete(p);
			}
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock ");
		}
	}

	@Override
	public List<TipoProducto> SelectTodos() {
		List<TipoProducto> list = null;
		try {
			list = dao.findAll();
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}
		return list;
	}
	
	//Metodos Unicos
	public TipoProducto buscarTipoProducto(TipoProducto objeto) {
		TipoProducto tipoProducto = null;
		try {
			Optional<TipoProducto> aux = dao.findById(objeto.getIdTipoProducto());
			if (aux.isEmpty()) {
				throw new Exception("El Tipo de Producto que busca NO EXISTE");
			} else {
				tipoProducto = aux.get();
			}
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}

		return tipoProducto;
	}

	@Override
	public TipoProducto buscar(Long id) {
		// Platilla no utilizada
		return null;
	}

}
