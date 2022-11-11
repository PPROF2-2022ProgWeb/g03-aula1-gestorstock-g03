package com.ar.Grupo3.data.objects.classesbusiness;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.Grupo3.data.objects.interfaces.DaoDepartamentoIntf;
import com.ar.Grupo3.data.objects.repositorio.DepartamentoRepositorio;
import com.ar.Grupo3.model.Departamento;

@Service
public class DaoDepartamentoImpl implements Serializable, DaoDepartamentoIntf {

    private static final long serialVersionUID = 6163569362993492170L;

    @Autowired
    private DepartamentoRepositorio dao;

    @Override
    public Departamento buscar(Long id) {
        // Plantilla no utilizada
        return null;
    }

    @Override
    public Departamento selectPorId(Long id) {

        Departamento departamento = null;
        try {
            Optional<Departamento> aux = dao.findById(id);
            if (aux.isEmpty()) {
                throw new Exception("El departamento que busca NO EXISTE");
            } else {
                departamento = aux.get();
            }
        } catch (Exception e) {
            LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
                    + " } fin del error preguntar al Grupo 3 ==> GestorStock");
        }

        return departamento;
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
    public void agregar(Departamento p) {
        try {
            if (p.getDepto().isEmpty()) {
                throw new Exception("El Departamento que va a registrar no tiene Nombre");
            }

            dao.save(p);

        } catch (Exception e) {
            LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
                    + " } fin del error preguntar al Grupo 3 ==> GestorStock");
        }

    }

    @Override
    public void modificar(Departamento p) {
        try {
            if (p.getIdDepto() == null) {
                throw new Exception("El Departamento que va a registrar no tiene identificador del mismo");
            }
            if (p.getDepto().isEmpty()) {
                throw new Exception("El Departamento que va a registrar no tiene Nombre");
            }
            dao.save(p);

        } catch (Exception e) {
            LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
                    + " } fin del error preguntar al Grupo 3 ==> GestorStock");
        }

    }

    @Override
    public void borrar(Departamento p) {
        try {
            if (p.getIdDepto() == null) {
                throw new Exception("El departamento que va a eliminar no tiene identificador");
            }
            // Revisamos que el pedido exista
            p = buscarDepartamento(p);
            if (p != null) {
                dao.delete(p);
            }
        } catch (Exception e) {
            LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
                    + " } fin del error preguntar al Grupo 3 ==> GestorStock ");
        }

    }

    @Override
    public List<Departamento> SelectTodos() {
        List<Departamento> list = null;
        try {
            list = dao.findAll();
        } catch (Exception e) {
            LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
                    + " } fin del error preguntar al Grupo 3 ==> GestorStock");
        }
        return list;
    }

    // Metotos unicos de clase
    public Departamento buscarDepartamento(Departamento objeto) {
        Departamento departamento = null;
        try {
            Optional<Departamento> aux = dao.findById(objeto.getIdDepto());
            if (aux.isEmpty()) {
                throw new Exception("El departamento que busca NO EXISTE");
            } else {
                departamento = aux.get();
            }
        } catch (Exception e) {
            LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
                    + " } fin del error preguntar al Grupo 3 ==> GestorStock");
        }

        return departamento;
    }

}
