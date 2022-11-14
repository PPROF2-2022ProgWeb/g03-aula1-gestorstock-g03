package com.ar.Grupo3.service.wservice;

import java.io.Serializable;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ar.Grupo3.Security.Entity.Usuario;
import com.ar.Grupo3.data.factory.FabricaDAO;
import com.ar.Grupo3.data.objects.interfaces.DaoUsuarioIntf;
import com.ar.Grupo3.viewmodel.UsuarioModel;

@RestController
@RequestMapping({"/gestor"})
@CrossOrigin(origins = {"http://localhost:4200", "https://gestor-stock.netlify.app", "https://gestor-stock-desa.netlify.app"})
public class WServiceUsuario implements Serializable {

    private static final long serialVersionUID = -312339292846758441L;

    @Autowired
    private DaoUsuarioIntf servicioUsuario = FabricaDAO.obtenerUsuario();

    /*
	 * ----------------------------------------------------------------------------
	 * Usuario
	 * ----------------------------------------------------------------------------
     */
    @GetMapping(path = "/usuario")
    public List<UsuarioModel> mostrarUsuario() {
        List<UsuarioModel> listaUsuario = listar();
        return listaUsuario;
    }

    @GetMapping(path = "/usuario/{id}")
    public UsuarioModel buscarUsuario(@PathVariable("id") Long id) {
        return buscarPorId(id);
    }

    @PostMapping(path = "/usuario")
    public Usuario AgregarUsuario(@RequestBody Usuario Usuario) {
        return agregamos(Usuario);
    }

    @PutMapping(path = "/usuario/{id}")
    public ResponseEntity<Usuario> modificarUsuario(@PathVariable("id") Long id, @RequestBody Usuario usuario) {
        try {
            if (usuario != null) {
                modificamos(usuario, id);
                return new ResponseEntity<Usuario>(HttpStatus.OK);
            } else {
                throw new Exception("El objeto a modificar esta vacio");
            }
        } catch (Exception e) {
            return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/usuario/{id}")
    public ResponseEntity<Usuario> eliminarUsuario(@PathVariable("id") Long id) {
        int var = eliminar(id);
        try {
            if (var != 0) {
                return new ResponseEntity<Usuario>(HttpStatus.OK);
            } else {
                throw new Exception("El identificador del abono eliminar esta vacio");
            }
        } catch (Exception e) {
            return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
        }
    }

    // __________________________________________________________________________________
    // Logica
    // __________________________________________________________________________________
    // Buscamos todos los objetos Usuario
    public List<UsuarioModel> listar() {
        return servicioUsuario.SelectTodos();
    }

    // Buscamos un objeto UsuarioModel por ID
    public UsuarioModel buscarPorId(Long id) {
        return servicioUsuario.selectPorId(id);
    }

    // Buscamos un objeto Usuario por ID
    public Usuario buscar(Long id) {
        return servicioUsuario.buscar(id);
    }

    // Agregamos un objeto Usuario por ID
    public Usuario agregamos(Usuario Usuario) {
        servicioUsuario.agregar(Usuario);
        return Usuario;
    }

    // modificamos el objeto Usuario
    public void modificamos(Usuario UsuarioModificado, Long id) {
        // Verificamos que no nos llegue nulo el id
        if (id != null) {
            Usuario busca = servicioUsuario.buscar(id);
            // Ahora si modificamos la Usuario
            if (busca != null) {
                servicioUsuario.modificar(UsuarioModificado);
            }
        }
    }

    // Eliminamos el objeto Usuario
    public Integer eliminar(Long id) {
        Integer aux = 1;
        // nos fijamos que no nos llegue nulo
        try {
            if (id != null) {
                Usuario elimina = new Usuario(id);
                // eliminar el objeto Usuario
                if (elimina != null) {
                    servicioUsuario.borrar(elimina);
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
