package com.ar.Grupo3.seguridad.negocio;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.Grupo3.Security.Entity.Rol;
import com.ar.Grupo3.Security.Entity.Usuario;
import com.ar.Grupo3.Security.Repository.IUsuarioRepository;
import com.ar.Grupo3.data.objects.interfaces.DaoUsuarioIntf;
import com.ar.Grupo3.data.objects.repositorio.*;
import com.ar.Grupo3.model.*;
import com.ar.Grupo3.util.EncriptarPassword;
import com.ar.Grupo3.util.MensajesObjetos;
import com.ar.Grupo3.util.Utilidades;
import com.ar.Grupo3.viewmodel.UsuarioModel;

@Service
@Transactional
public class DaoUsuarioImpl implements Serializable, DaoUsuarioIntf {

	private static final long serialVersionUID = -3664593050802216890L;

	@Autowired
	private IUsuarioRepository dao;

	@Autowired
	private ProvinciaRepositorio provincia;

	// Solo para el LOGIN
	public Optional<Usuario> obtenerNombreUsuario(String nombre) {
		return Optional.ofNullable(dao.findByUsername(nombre));
	}

	public boolean existeNombreUsuario(String nombre) {
		boolean estado = false;
		Usuario aux = null;
		try {
			aux = dao.findByUsername(nombre);
			estado = Utilidades.existe(aux);
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}
		return estado;
	}

	@Override
	public Usuario buscar(Long id) {

		Usuario usuario = null;
		try {
			Optional<Usuario> aux = dao.findByIdUsuario(id);
			if (aux.isEmpty()) {
				throw new Exception("El usuario que busca NO EXISTE");
			} else {
				usuario = aux.get();
			}
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}

		return usuario;
	}

	@Override
	public UsuarioModel selectPorId(Long id) {

		UsuarioModel usuario = new UsuarioModel();
		try {
			Optional<Usuario> aux = dao.findByIdUsuario(id);
			if (aux.isEmpty()) {
				throw new Exception("El usuario que busca NO EXISTE");
			} else {
				Usuario obj = aux.get();

				Provincia cia = buscarProvincia(obj);

				// Completamos el objeto que mostraremos a la vista.
				usuario.setCedula(obj.getCedula());
				usuario.setIdUsuario(obj.getIdUsuario());
				usuario.setIdProvincia(obj.getIdProvincia());
				usuario.setApellido(obj.getApellido());
				usuario.setNombre(obj.getNombre());
				usuario.setNombreUsuario(obj.getUsername());
				usuario.setContrasenia(obj.getPassword());
				usuario.setCelular(obj.getCelular());
				usuario.setCorreo(obj.getCorreo());
				usuario.setDireccion(obj.getDireccion());
				usuario.setTel(obj.getTel());
				usuario.setNombreRol(buscarRoles(obj));

				// si existe le asignamos el nombre si no un nombre predeterminado
				if (cia != null) {
					usuario.setNombreProvincia(cia.getNombreProvincia());
				} else {
					usuario.setNombreProvincia(MensajesObjetos.PROVINCIA_NO_RELACIONADA);
				}

			}
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}

		return usuario;
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
	public void agregar(Usuario p) {
		try {
			if (p.getUsername().isEmpty()) {
				throw new Exception("El Usuario que va a registrar no tiene nombre de usuario");
			}
			if (p.getPassword().isEmpty()) {
				throw new Exception("El Usuario que va a registrar no tiene Contrasenia");
			} else {
				p.setPassword(EncriptarPassword.encriptarPassword(p.getPassword()));
			}

			dao.save(p);

		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}

	}

	@Override
	public void modificar(Usuario p) {
		try {
			if (p.getIdUsuario() == null) {
				throw new Exception("El Usuario que va a registrar no tiene identificador del mismo");
			}
			if (p.getUsername().isEmpty()) {
				throw new Exception("El Usuario que va a registrar no tiene nombre de usuario");
			}
			if (p.getPassword().isEmpty()) {
				throw new Exception("El Usuario que va a registrar no tiene Contrasenia");
			} else {
				p.setPassword(EncriptarPassword.encriptarPassword(p.getPassword()));
			}

			dao.save(p);

		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}

	}

	@Override
	public void borrar(Usuario p) {
		try {
			if (p.getIdUsuario() == null) {
				throw new Exception("El usuario a eliminar no tiene identificador");
			}
			// Revisamos que El Usuario exista
			p = buscar(p.getIdUsuario());
			if (p != null) {
				dao.delete(p);
			}

		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock ");
		}
	}

	@Override
	public List<UsuarioModel> SelectTodos() {
		List<UsuarioModel> list = null;
		try {
			List<Usuario> usuarios = dao.findAll();

			// Si la lista no esta vacia
			if (!usuarios.isEmpty()) {

				list = new LinkedList<>();

				// Recorremos la lista
				for (Usuario usuario : usuarios) {
					// Buscamos id por id
					Provincia cia = buscarProvincia(usuario);

					// Este es el nuevo objeto que se mostrar para la vista
					UsuarioModel nuevoObjeto = new UsuarioModel();

					nuevoObjeto.setCedula(usuario.getCedula());
					nuevoObjeto.setIdUsuario(usuario.getIdUsuario());
					nuevoObjeto.setApellido(usuario.getApellido());
					nuevoObjeto.setNombre(usuario.getNombre());
					nuevoObjeto.setNombreUsuario(usuario.getUsername());
					nuevoObjeto.setContrasenia(usuario.getPassword());
					nuevoObjeto.setCelular(usuario.getCelular());
					nuevoObjeto.setCorreo(usuario.getCorreo());
					nuevoObjeto.setDireccion(usuario.getDireccion());
					nuevoObjeto.setTel(usuario.getTel());
					nuevoObjeto.setNombreRol(buscarRoles(usuario));

					// si existe le asignamos el nombre si no un nombre predeterminado
					if (cia != null) {
						nuevoObjeto.setNombreProvincia(cia.getNombreProvincia());
					} else {
						nuevoObjeto.setNombreProvincia(MensajesObjetos.PROVINCIA_NO_RELACIONADA);
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
	public boolean existeProvincia(Usuario objeto) {
		boolean encontrado = false;
		// Nos fijamos que los datos existan
		if (objeto != null) {
			if (buscarProvincia(objeto) != null) {
				encontrado = true;
			}
		}
		return encontrado;
	}

	public Provincia buscarProvincia(Usuario objeto) {
		Provincia fact = null;
		try {
			Optional<Provincia> faBusqueda = provincia.findById(objeto.getIdProvincia());

			if (faBusqueda.get().getIdProvincia().equals(objeto.getIdProvincia())) {
				fact = faBusqueda.get();
			} else {
				throw new Exception("El usuario que va a registrar no tiene Provincia creada");
			}
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}

		return fact;
	}

	public String buscarRoles(Usuario u) {

		String listaRoles = "";
		try {

			if (!u.getRoles().isEmpty()) {
				for (Rol rol : u.getRoles()) {
					listaRoles += rol.getNombreRol() + " ,";
				}
			}

			if (listaRoles.endsWith(" ,")) {
				listaRoles = listaRoles.substring(0, listaRoles.lastIndexOf(','));
			}
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}

		return listaRoles;
	}

}
