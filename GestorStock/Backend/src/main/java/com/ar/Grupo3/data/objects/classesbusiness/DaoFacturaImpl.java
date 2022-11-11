package com.ar.Grupo3.data.objects.classesbusiness;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.Grupo3.data.objects.interfaces.DaoFacturaIntf;
import com.ar.Grupo3.data.objects.repositorio.*;
import com.ar.Grupo3.model.*;
import com.ar.Grupo3.Security.Entity.Usuario;
import com.ar.Grupo3.Security.Repository.IUsuarioRepository;
import com.ar.Grupo3.util.MensajesObjetos;
import com.ar.Grupo3.viewmodel.FacturaModel;

@Service
public class DaoFacturaImpl implements Serializable, DaoFacturaIntf {

	private static final long serialVersionUID = 8297523269288157919L;

	@Autowired
	private FacturaRepositorio dao;

	@Autowired
	private IUsuarioRepository usuario;

	@Autowired
	private ProvinciaRepositorio provincia;

	@Override
	public Factura buscar(Long id) {

		Factura factura = null;
		try {
			Optional<Factura> aux = dao.findById(id);
			if (aux.isEmpty()) {
				throw new Exception("El factura que busca NO EXISTE");
			} else {
				factura = aux.get();
			}
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}

		return factura;
	}

	@Override
	public FacturaModel selectPorId(Long id) {

		FacturaModel factura = new FacturaModel();
		try {
			Optional<Factura> aux = dao.findById(id);
			if (aux.isEmpty()) {
				throw new Exception("El factura que busca NO EXISTE");
			} else {
				Factura obj = aux.get();
				
				Usuario usu = buscarUsuario(obj);
				
				Provincia cia = buscarProvincia(obj);

				// Completamos el objeto que mostraremos a la vista.
				factura.setDirEnvio(obj.getDirEnvio());
				factura.setIdFactura(obj.getIdFactura());
				factura.setIdProvincia(obj.getIdProvincia());
				factura.setIdUsuario(obj.getIdUsuario());
				factura.setTipo(obj.getTipo());
				factura.setTotal(obj.getTotal());
				factura.setFechaApertura(obj.getFechaApertura());
				factura.setFechaCierre(obj.getFechaCierre());
				factura.setNombreFactura(obj.getNombreFactura());

				if (usu != null) {
					factura.setNombreUsuario(usu.getUsername());
				} else {
					factura.setNombreFactura(MensajesObjetos.USUARIO_NO_RELACIONADO);
				}

				if (cia != null) {
					factura.setNombreProvincia(cia.getNombreProvincia());
				} else {
					factura.setNombreFactura(MensajesObjetos.PROVINCIA_NO_RELACIONADA);
				}
				
			}
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}

		return factura;
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
	public void agregar(Factura p) {
		try {
			if (p.getNombreFactura().isEmpty()) {
				throw new Exception("La Factura que va a registrar no tiene Nombre");
			}
			dao.save(p);

		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}
	}

	@Override
	public void modificar(Factura p) {
		try {
			if (p.getIdFactura() == null) {
				throw new Exception("La Factura que va a registrar no tiene identificador de la misma");
			}
			if (p.getNombreFactura().isEmpty()) {
				throw new Exception("La Factura que va a registrar no tiene Nombre");
			}

			dao.save(p);

		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}
	}

	@Override
	public void borrar(Factura p) {
		try {
			if (p.getIdFactura() == null) {
				throw new Exception("El factura que va a eliminar no tiene identificador");
			}
			// Revisamos que el pedido exista
			p = buscar(p.getIdFactura());
			if (p != null) {
				dao.delete(p);
			}
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock ");
		}

	}

	@Override
	public List<FacturaModel> SelectTodos() {
		List<FacturaModel> list = null;
		try {
			List<Factura> facturas = dao.findAll();

			// Si la lista no esta vacia
			if (!facturas.isEmpty()) {

				list = new LinkedList<>();

				// Recorremos la lista
				for (Factura factura : facturas) {
					// Buscamos id por id
					Usuario usu = buscarUsuario(factura);
					
					Provincia cia = buscarProvincia(factura);

					// Este es el nuevo objeto que se mostrara para la vista
					FacturaModel nuevoObjeto = new FacturaModel();
					nuevoObjeto.setDirEnvio(factura.getDirEnvio());
					nuevoObjeto.setIdFactura(factura.getIdFactura());
					nuevoObjeto.setIdProvincia(factura.getIdProvincia());
					nuevoObjeto.setIdUsuario(factura.getIdUsuario());
					nuevoObjeto.setFechaApertura(factura.getFechaApertura());
					nuevoObjeto.setFechaCierre(factura.getFechaCierre());
					nuevoObjeto.setTipo(factura.getTipo());
					nuevoObjeto.setTotal(factura.getTotal());
					nuevoObjeto.setNombreFactura(factura.getNombreFactura());

					// si existe le asignamos el nombre si no un nombre predeterminado
					if (usu != null) {
						nuevoObjeto.setNombreUsuario(usu.getUsername());
					} else {
						nuevoObjeto.setNombreFactura(MensajesObjetos.USUARIO_NO_RELACIONADO);
					}

					if (cia != null) {
						nuevoObjeto.setNombreProvincia(cia.getNombreProvincia());
					} else {
						nuevoObjeto.setNombreFactura(MensajesObjetos.PROVINCIA_NO_RELACIONADA);
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
	public boolean existeUsuario(Factura objeto) {
		boolean encontrado = false;
		// Nos fijamos que los datos existan
		if (objeto != null) {
			if (buscarUsuario(objeto) != null) {
				encontrado = true;
			}
		}
		return encontrado;
	}

	public boolean existeProvincia(Factura objeto) {
		boolean encontrado = false;
		// Nos fijamos que los datos existan
		if (objeto != null) {
			if (buscarProvincia(objeto) != null) {
				encontrado = true;
			}
		}
		return encontrado;
	}

	public Usuario buscarUsuario(Factura objeto) {
		Usuario a = null;
		try {
			Optional<Usuario> faBusqueda = usuario.findByIdUsuario(objeto.getIdUsuario());

			if (faBusqueda.get().getIdUsuario().equals(objeto.getIdUsuario())) {
				a = faBusqueda.get();
			} else {
				throw new Exception("El factura que va a registrar no tiene Usuario creado");
			}
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}

		return a;
	}

	public Provincia buscarProvincia(Factura objeto) {
		Provincia a = null;
		try {
			Optional<Provincia> faBusqueda = provincia.findById(objeto.getIdProvincia());

			if (faBusqueda.get().getIdProvincia().equals(objeto.getIdProvincia())) {
				a = faBusqueda.get();
			} else {
				throw new Exception("El factura que va a registrar no tiene Provincia creada");
			}
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}

		return a;
	}

}
