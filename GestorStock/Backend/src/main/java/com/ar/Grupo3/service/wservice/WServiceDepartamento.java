package com.ar.Grupo3.service.wservice;

import java.io.Serializable;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ar.Grupo3.data.factory.FabricaDAO;
import com.ar.Grupo3.data.objects.interfaces.DaoDepartamentoIntf;
import com.ar.Grupo3.model.Departamento;

@RestController
@RequestMapping({"/gestor"})
@CrossOrigin(origins = {"http://localhost:4200", "https://gestor-stock.netlify.app", "https://gestor-stock-desa.netlify.app"})
public class WServiceDepartamento implements Serializable {

    private static final long serialVersionUID = 6055336153691458878L;

    @Autowired
    private DaoDepartamentoIntf servicioDepartamento = FabricaDAO.obtenerDepartamento();

    /*
	 * ----------------------------------------------------------------------------
	 * Departamento
	 * ----------------------------------------------------------------------------
     */
    @GetMapping(path = "/departamento")
    public List<Departamento> mostrarDepartamento() {
        List<Departamento> listaDepartamento = listar();
        return listaDepartamento;
    }

    @GetMapping(path = "/departamento/{id}")
    public Departamento buscarDepartamento(@PathVariable("id") Long id) {
        return buscarPorId(id);
    }

    @PostMapping(path = "/departamento")
    public Departamento AgregarDepartamento(@RequestBody Departamento Departamento) {
        return agregamos(Departamento);
    }

    @PutMapping(path = "/departamento/{id}")
    public ResponseEntity<Departamento> modificarDepartamento(@PathVariable("id") Long id,
            @RequestBody Departamento departamento) {
        try {
            if (departamento != null) {
                modificamos(departamento, id);
                return new ResponseEntity<Departamento>(HttpStatus.OK);
            } else {
                throw new Exception("El objeto a modificar esta vacio");
            }
        } catch (Exception e) {
            return new ResponseEntity<Departamento>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(path = "/departamento/{id}")
    public ResponseEntity<Departamento> eliminarDepartamento(@PathVariable("id") Long id) {
        int var = eliminar(id);
        try {
            if (var != 0) {
                return new ResponseEntity<Departamento>(HttpStatus.OK);
            } else {
                throw new Exception("El identificador del abono eliminar esta vacio");
            }
        } catch (Exception e) {
            return new ResponseEntity<Departamento>(HttpStatus.NOT_FOUND);
        }
    }

    // __________________________________________________________________________________
    // Logica
    // __________________________________________________________________________________
    // Bucamos todos las Departamento
    public List<Departamento> listar() {
        return servicioDepartamento.SelectTodos();
    }

    // Buscamos un Departamento por ID
    public Departamento buscarPorId(Long id) {
        return servicioDepartamento.selectPorId(id);
    }

    // Agregamos un por ID
    public Departamento agregamos(Departamento Departamento) {
        servicioDepartamento.agregar(Departamento);
        return Departamento;
    }

    // modificamos el objeto Departamento
    public void modificamos(Departamento DepartamentoModificado, Long id) {
        // Verificamos que no nos llegue nulo el id
        if (id != null) {
            Departamento busca = servicioDepartamento.selectPorId(id);
            // Ahora si modificamos la Departamento
            if (busca != null) {
                servicioDepartamento.modificar(DepartamentoModificado);
            }
        }
    }

    // Eliminamos el objeto Departamento
    public Integer eliminar(Long id) {
        Integer aux = 1;
        // nos fijamos que no nos llegue nulo
        try {
            if (id != null) {
                Departamento elimina = new Departamento(id);
                // eliminar el objeto Departamento
                if (elimina != null) {
                    servicioDepartamento.borrar(elimina);
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
