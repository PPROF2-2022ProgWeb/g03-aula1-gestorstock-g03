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
import com.ar.Grupo3.Security.Enums.NombresRol;
import com.ar.Grupo3.Security.Repository.IRolRepository;
import com.ar.Grupo3.Security.Repository.IUsuarioRepository;
import com.ar.Grupo3.data.objects.interfaces.DaoRolIntf;
import com.ar.Grupo3.util.MensajesObjetos;
import com.ar.Grupo3.util.Utilidades;
import com.ar.Grupo3.viewmodel.RolModel;

@Service
@Transactional
public class DaoRolImpl implements Serializable, DaoRolIntf {

    private static final long serialVersionUID = 5578315861332036707L;

    @Autowired
    IRolRepository dao;

    @Autowired
    IUsuarioRepository usuario;

    // Solo para el LOGIN
    public Optional<Rol> obtenerNombreRol(NombresRol nombres) {
        Optional<Rol> aux = null;
        try {
            List<Rol> laLista = dao.findByNombreRol(nombres);
            if (Utilidades.tieneDatos(laLista)) {
                aux = Optional.ofNullable(laLista.get(0));
            }
        } catch (Exception e) {
        }
        return aux;
    }

    @Override
    public Rol buscar(Long id) {

        Rol rol = null;
        try {
            Optional<Rol> aux = dao.findById(id);
            if (aux.isEmpty()) {
                throw new Exception("El delivery que busca NO EXISTE");
            } else {
                rol = aux.get();
            }
        } catch (Exception e) {
            LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
                    + " } fin del error preguntar al Grupo 3 ==> GestorStock");
        }

        return rol;
    }

    @Override
    public RolModel selectPorId(Long id) {

        RolModel rol = new RolModel();
        try {
            Optional<Rol> aux = dao.findById(id);
            if (aux.isEmpty()) {
                throw new Exception("El rol que busca NO EXISTE");
            } else {
                Rol obj = aux.get();

                Usuario usu = buscarUsuario(obj);
                // Completamos el objeto que mostraremos a la vista.
                rol.setIdRol(obj.getIdRol());
                rol.setIdUsuario(obj.getIdUsuario());
                rol.setNombreRol(obj.getNombreRol().name());
                rol.setNombreUsuario(
                        (usu.getIdUsuario() != null) ? usu.getUsername() : MensajesObjetos.USUARIO_NO_RELACIONADO);

                if (rol.getIdUsuario() != null) {
                    rol.setNombreUsuario(buscarUsuario(obj).getUsername());
                } else {

                }
            }
        } catch (Exception e) {
            LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
                    + " } fin del error preguntar al Grupo 3 ==> GestorStock");
        }

        return rol;
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
    public void agregar(Rol p) {
        try {
            if (p.getIdUsuario() == null) {
                throw new Exception("El rol que va a registrar no tiene identificador de Usuario");
            }
            if (p.getNombreRol().name().isEmpty()) {
                throw new Exception("El Rol que va a registrar no tiene Nombre");
            }
            // Controlamos que todo este en orden
            if (existeUsuario(p)) {
                dao.save(p);
            }

        } catch (Exception e) {
            LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
                    + " } fin del error preguntar al Grupo 3 ==> GestorStock");
        }

    }

    @Override
    public void modificar(Rol p) {
        try {
            if (p.getIdRol() == null) {
                throw new Exception("El objeto a persistir no tiene identificador");
            }
            if (p.getIdUsuario() == null) {
                throw new Exception("La devolucion que va a registrar no tiene identificador de Usuario");
            }
            if (p.getNombreRol().name().isEmpty()) {
                throw new Exception("El Rol que va a registrar no tiene Nombre");
            }
            // Controlamos que todo este en orden
            if (existeUsuario(p)) {
                dao.save(p);
            }

        } catch (Exception e) {
            LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
                    + " } fin del error preguntar al Grupo 3 ==> GestorStock");
        }
    }

    @Override
    public void borrar(Rol p) {
        try {
            if (p.getIdRol() == null) {
                throw new Exception("El rol a eliminar no tiene identificador");
            }
            // Revisamos que El Rol exista
            p = buscar(p.getIdRol());
            if (p != null) {
                dao.delete(p);
            }

        } catch (Exception e) {
            LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
                    + " } fin del error preguntar al Grupo 3 ==> GestorStock ");
        }
    }

    @Override
    public List<RolModel> SelectTodos() {
        List<RolModel> list = null;
        try {
            List<Rol> rols = dao.findAll();

            // Si la lista no esta vacia
            if (!rols.isEmpty()) {

                list = new LinkedList<>();

                // Recorremos la lista
                for (Rol rol : rols) {
                    // Buscamos id por id
                    Usuario aux = buscarUsuario(rol);

                    // Este es el nuevo objeto que se mostrar para la vista
                    RolModel nuevoObjeto = new RolModel();

                    nuevoObjeto.setIdRol(rol.getIdRol());
                    nuevoObjeto.setIdUsuario(rol.getIdUsuario());
                    nuevoObjeto.setNombreRol(rol.getNombreRol().name());

                    // si existe le asignamos el nombre si no un nombre predeterminado
                    if (aux != null) {
                        nuevoObjeto.setNombreUsuario(aux.getUsername());
                    } else {
                        nuevoObjeto.setNombreUsuario(MensajesObjetos.USUARIO_NO_RELACIONADO);
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
    public boolean existeUsuario(Rol objeto) {
        boolean encontrado = false;
        // Nos fijamos que los datos existan
        if (objeto != null) {
            if (buscarUsuario(objeto) != null) {
                encontrado = true;
            }
        }
        return encontrado;
    }

    public Usuario buscarUsuario(Rol objeto) {
        Usuario fact = null;
        try {
            Optional<Usuario> faBusqueda = usuario.findByIdUsuario(objeto.getIdUsuario());

            if (faBusqueda.get().getIdUsuario().equals(objeto.getIdUsuario())) {
                fact = faBusqueda.get();
            } else {
                throw new Exception("El rol que va a registrar no tiene Usuario creada");
            }
        } catch (Exception e) {
            LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
                    + " } fin del error preguntar al Grupo 3 ==> GestorStock");
        }

        return fact;
    }
}
