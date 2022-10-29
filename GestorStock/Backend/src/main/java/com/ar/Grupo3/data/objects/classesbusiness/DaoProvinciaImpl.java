package com.ar.Grupo3.data.objects.classesbusiness;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.Grupo3.data.objects.interfaces.DaoProvinciaIntf;
import com.ar.Grupo3.data.objects.repositorio.ProvinciaRepositorio;
import com.ar.Grupo3.data.objects.repositorio.DepartamentoRepositorio;
import com.ar.Grupo3.util.MensajesObjetos;
import com.ar.Grupo3.viewmodel.ProvinciaModel;
import com.ar.Grupo3.model.Provincia;
import com.ar.Grupo3.model.Departamento;

@Service
public class DaoProvinciaImpl implements Serializable, DaoProvinciaIntf {

	private static final long serialVersionUID = -2569349388711599788L;

	@Autowired
	private ProvinciaRepositorio dao;

	@Autowired
	private DepartamentoRepositorio departamento;

	@Override
	public Provincia buscar(Long id) {

		Provincia provincia = null;
		try {
			Optional<Provincia> aux = dao.findById(id);
			if (aux.isEmpty()) {
				throw new Exception("El provincia que busca NO EXISTE");
			} else {
				provincia = aux.get();
			}
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}

		return provincia;
	}
	
	@Override
	public ProvinciaModel selectPorId(Long id) {

		ProvinciaModel provincia = new ProvinciaModel();
		try {
			Optional<Provincia> aux = dao.findById(id);
			if (aux.isEmpty()) {
				throw new Exception("El provincia que busca NO EXISTE");
			} else {
				Provincia obj = aux.get();

				Departamento dep = buscarDepartamento(obj);
				// Completamos el objeto que mostraremos a la vista.
				provincia.setNombreProvincia(obj.getNombreProvincia());
				provincia.setIdProvincia(obj.getIdProvincia());
				provincia.setIdDepto(obj.getIdDepto());
				provincia.setNombreDepto((dep != null)? dep.getDepto() : MensajesObjetos.DEPARTAMENTO_NO_RELACIONADO);

			}
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}

		return provincia;
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
	public void agregar(Provincia p) {
		try {
			if (p.getIdDepto() == null) {
				throw new Exception("La Provincia que va a registrar no tiene identificador de Departamento");
			}
			if (p.getNombreProvincia().isEmpty()) {
				throw new Exception("El Provincia que va a registrar no tiene Nombre");
			}
			// Controlamos que todo este en orden
			if (buscarDepartamento(p) != null) {
				dao.save(p);
			}

		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}
	}

	@Override
	public void modificar(Provincia p) {
		try {
			if (p.getIdProvincia() == null) {
				throw new Exception("La Provincia que va a registrar no tiene identificador de la misma");
			}
			if (p.getIdDepto() == null) {
				throw new Exception("La Provincia que va a registrar no tiene identificador de Departamento");
			}
			if (p.getNombreProvincia().isEmpty()) {
				throw new Exception("El Provincia que va a registrar no tiene Nombre");
			}
			// Controlamos que todo este en orden
			if (buscarDepartamento(p) != null) {
				dao.save(p);
			}

		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}

	}

	@Override
	public void borrar(Provincia p) {
		try {
			if (p.getIdProvincia() == null) {
				throw new Exception("El provincia a eliminar no tiene identificador");
			}
			// Revisamos que El Provincia exista
			p = buscar(p.getIdProvincia());
			if (p != null) {
				dao.delete(p);
			}

		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock ");
		}
	}

	@Override
	public List<ProvinciaModel> SelectTodos() {
		List<ProvinciaModel> list = null;
		try {
			List<Provincia> provincias = dao.findAll();

			// Si la lista no esta vacia
			if (!provincias.isEmpty()) {

				list = new LinkedList<>();

				// Recorremos la lista
				for (Provincia provincia : provincias) {
					// Buscamos id por id
					Departamento aux = buscarDepartamento(provincia);

					//Este es el nuevo objeto que se mostrar para la vista
					ProvinciaModel nuevoObjeto = new ProvinciaModel();
					
					nuevoObjeto.setIdProvincia(provincia.getIdProvincia());
					nuevoObjeto.setIdDepto(provincia.getIdDepto());
					nuevoObjeto.setNombreProvincia(provincia.getNombreProvincia());

					// si existe le asignamos el nombre si no un nombre predeterminado
					if (aux != null) {
						nuevoObjeto.setNombreDepto(aux.getDepto());
					} else {
						nuevoObjeto.setNombreDepto(MensajesObjetos.DEPARTAMENTO_NO_RELACIONADO);
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
	public boolean existeDepartamento(Provincia objeto) {
		boolean encontrado = false;
		// Nos fijamos que los datos existan
		if (objeto != null) {
			if (buscarDepartamento(objeto) != null) {
				encontrado = true;
			}
		}
		return encontrado;
	}

	public Departamento buscarDepartamento(Provincia objeto) {
		Departamento fact = null;
		try {
			Optional<Departamento> faBusqueda = departamento.findById(objeto.getIdDepto());

			if (faBusqueda.get().getIdDepto().equals(objeto.getIdDepto())) {
				fact = faBusqueda.get();
			} else {
				throw new Exception("El provincia que va a registrar no tiene Departamento creada");
			}
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}

		return fact;
	}

}
