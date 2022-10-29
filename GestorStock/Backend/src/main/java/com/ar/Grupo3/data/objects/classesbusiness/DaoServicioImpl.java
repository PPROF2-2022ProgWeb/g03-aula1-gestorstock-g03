package com.ar.Grupo3.data.objects.classesbusiness;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.Grupo3.data.objects.interfaces.DaoServicioIntf;
import com.ar.Grupo3.data.objects.repositorio.*;
import com.ar.Grupo3.model.*;
import com.ar.Grupo3.util.MensajesObjetos;
import com.ar.Grupo3.viewmodel.ServicioModel;

@Service
public class DaoServicioImpl implements Serializable, DaoServicioIntf {

	private static final long serialVersionUID = 8430935781626817033L;

	@Autowired
	private ServicioRepositorio dao;

	@Autowired
	private FacturaRepositorio factura;

	@Override
	public Servicio buscar(Long id) {

		Servicio servicio = null;
		try {
			Optional<Servicio> aux = dao.findById(id);
			if (aux.isEmpty()) {
				throw new Exception("El servicio que busca NO EXISTE");
			} else {
				servicio = aux.get();
			}
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}

		return servicio;
	}

	@Override
	public ServicioModel selectPorId(Long id) {

		ServicioModel servicio = new ServicioModel();
		try {
			Optional<Servicio> aux = dao.findById(id);
			if (aux.isEmpty()) {
				throw new Exception("El servicio que busca NO EXISTE");
			} else {
				Servicio obj = aux.get();

				
				Factura fac = buscarFactura(obj);
				// Completamos el objeto que mostraremos a la vista.
				servicio.setEstado(obj.getEstado());
				servicio.setIdServicio(obj.getIdServicio());
				servicio.setIdFactura(obj.getIdFactura());
				servicio.setDescripcion(obj.getDescripcion());
				servicio.setNombreServicio(obj.getNombreServicio());
				servicio.setValor(obj.getValor());

				// si existe le asignamos el nombre si no un nombre predeterminado
				if (fac != null) {
					servicio.setNombreFactura(fac.getNombreFactura());
				} else {
					servicio.setNombreFactura(MensajesObjetos.FACTURA_NO_RELACIONADA);
				}
			}
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}

		return servicio;
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
	public void agregar(Servicio p) {
		try {
			if (p.getNombreServicio().isEmpty()) {
				throw new Exception("El Servicio que va a registrar no tiene Nombre");
			}
			dao.save(p);

		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}
	}

	@Override
	public void modificar(Servicio p) {
		try {
			if (p.getIdServicio() == null) {
				throw new Exception("El servicio que va a registrar no tiene identificador del mismo");
			}
			if (p.getNombreServicio().isEmpty()) {
				throw new Exception("El Servicio que va a registrar no tiene Nombre");
			}
			dao.save(p);

		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}
	}

	@Override
	public void borrar(Servicio p) {
		try {
			if (p.getIdServicio() == null) {
				throw new Exception("El servicio a eliminar no tiene identificador");
			}
			// Revisamos que El Servicio exista
			p = buscar(p.getIdServicio());
			if (p != null) {
				dao.delete(p);
			}

		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock ");
		}
	}

	@Override
	public List<ServicioModel> SelectTodos() {
		List<ServicioModel> list = null;
		try {
			List<Servicio> servicios = dao.findAll();

			// Si la lista no esta vacia
			if (!servicios.isEmpty()) {

				list = new LinkedList<>();

				// Recorremos la lista
				for (Servicio servicio : servicios) {
					// Buscamos id por id
					Factura aux = buscarFactura(servicio);

					// Este es el nuevo objeto que se mostrar para la vista
					ServicioModel nuevoObjeto = new ServicioModel();
					
					nuevoObjeto.setEstado(servicio.getEstado());
					nuevoObjeto.setIdServicio(servicio.getIdServicio());
					nuevoObjeto.setIdFactura(servicio.getIdFactura());
					nuevoObjeto.setDescripcion(servicio.getDescripcion());
					nuevoObjeto.setNombreServicio(servicio.getNombreServicio());
					nuevoObjeto.setValor(servicio.getValor());

					// si existe le asignamos el nombre si no un nombre predeterminado
					if (aux != null) {
						nuevoObjeto.setNombreFactura(aux.getNombreFactura());
					} else {
						nuevoObjeto.setNombreFactura(MensajesObjetos.FACTURA_NO_RELACIONADA);
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
	public boolean existeFactura(Servicio objeto) {
		boolean encontrado = false;
		// Nos fijamos que los datos existan
		if (objeto != null) {
			if (buscarFactura(objeto) != null) {
				encontrado = true;
			}
		}
		return encontrado;
	}

	public Factura buscarFactura(Servicio objeto) {
		Factura fact = null;
		try {
			Optional<Factura> faBusqueda = factura.findById(objeto.getIdFactura());

			if (faBusqueda.get().getIdFactura().equals(objeto.getIdFactura())) {
				fact = faBusqueda.get();
			} else {
				throw new Exception("El servicio que va a registrar no tiene Factura creada");
			}
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}

		return fact;
	}

}
