package com.ar.Grupo3.data.objects.classesbusiness;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.Grupo3.data.objects.interfaces.DaoAbonoIntf;
import com.ar.Grupo3.data.objects.repositorio.AbonoRepositorio;
import com.ar.Grupo3.data.objects.repositorio.FacturaRepositorio;
import com.ar.Grupo3.model.Abono;
import com.ar.Grupo3.model.Factura;
import com.ar.Grupo3.util.ConvertidorFecha;
import com.ar.Grupo3.util.MensajesObjetos;
import com.ar.Grupo3.viewmodel.AbonoModel;

@Service
public class DaoAbonoImpl implements Serializable, DaoAbonoIntf {

    private static final long serialVersionUID = 5993886576535616383L;

    @Autowired
    private AbonoRepositorio dao;

    @Autowired
    private FacturaRepositorio factura;

    @Override
    public Abono buscar(Long id) {

        Abono abono = null;
        try {
            Optional<Abono> aux = dao.findById(id);
            if (aux.isEmpty()) {
                throw new Exception("El delivery que busca NO EXISTE");
            } else {
                abono = aux.get();
            }
        } catch (Exception e) {
            LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
                    + " } fin del error preguntar al Grupo 3 ==> GestorStock");
        }

        return abono;
    }

    @Override
    public AbonoModel selectPorId(Long id) {

        AbonoModel abono = new AbonoModel();
        try {
            Optional<Abono> aux2 = dao.findById(id);
            if (aux2.isEmpty()) {
                throw new Exception("El abono que busca NO EXISTE");
            } else {
                Abono obj = aux2.get();

                Factura aux = buscarFactura(obj);

                // Completamos el objeto que mostraremos a la vista.
                abono.setFecha(obj.getFecha());
                abono.setIdAbono(obj.getIdAbono());
                abono.setIdFactura(obj.getIdFactura());
                abono.setValor(obj.getValor());
                abono.setNombreAbono(obj.getNombreAbono());

                // si existe le asignamos el nombre si no un nombre predeterminado
                if (aux != null) {
                    abono.setNombreFactura(aux.getNombreFactura());
                } else {
                    abono.setNombreFactura(MensajesObjetos.FACTURA_NO_RELACIONADA);
                }

            }
        } catch (Exception e) {
            LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
                    + " } fin del error preguntar al Grupo 3 ==> GestorStock");
        }

        return abono;
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
    public void agregar(Abono p) {
        try {
            if (p.getIdFactura() == null) {
                throw new Exception("El abono que va a registrar no tiene identificador de Factura");
            }
            if (p.getNombreAbono().isEmpty()) {
                throw new Exception("El abono que va a registrar no tiene Nombre");
            }
            if (p.getFecha() == null) {
                p.setFecha(ConvertidorFecha.FechaActual());
            }
            // Controlamos que todo este en orden
            if (existeFactura(p)) {
                dao.save(p);
            }

        } catch (Exception e) {
            LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
                    + " } fin del error preguntar al Grupo 3 ==> GestorStock");
        }

    }

    @Override
    public void modificar(Abono p) {
        try {
            if (p.getIdAbono() == null) {
                throw new Exception("El objeto a persistir no tiene identificador");
            }
            if (p.getIdFactura() == null) {
                throw new Exception("La devolucion que va a registrar no tiene identificador de Libro");
            }
            if (p.getNombreAbono().isEmpty()) {
                throw new Exception("El abono que va a registrar no tiene Nombre");
            }
            if (p.getFecha() == null) {
                p.setFecha(ConvertidorFecha.FechaActual());
            }
            // Controlamos que todo este en orden
            if (existeFactura(p)) {
                dao.save(p);
            }

        } catch (Exception e) {
            LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
                    + " } fin del error preguntar al Grupo 3 ==> GestorStock");
        }
    }

    @Override
    public void borrar(Abono p) {
        try {
            if (p.getIdAbono() == null) {
                throw new Exception("El abono a eliminar no tiene identificador");
            }
            // Revisamos que El Abono exista
            p = buscar(p.getIdAbono());
            if (p != null) {
                dao.delete(p);
            }

        } catch (Exception e) {
            LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
                    + " } fin del error preguntar al Grupo 3 ==> GestorStock ");
        }
    }

    @Override
    public List<AbonoModel> SelectTodos() {
        List<AbonoModel> list = null;
        try {
            List<Abono> abonos = dao.findAll();

            // Si la lista no esta vacia
            if (!abonos.isEmpty()) {

                list = new LinkedList<>();

                // Recorremos la lista
                for (Abono abono : abonos) {
                    // Buscamos id por id
                    Factura aux = buscarFactura(abono);

                    // Este es el nuevo objeto que se mostrar para la vista
                    AbonoModel nuevoObjeto = new AbonoModel();
                    nuevoObjeto.setFecha(abono.getFecha());
                    nuevoObjeto.setIdAbono(abono.getIdAbono());
                    nuevoObjeto.setIdFactura(abono.getIdFactura());
                    nuevoObjeto.setValor(abono.getValor());
                    nuevoObjeto.setNombreAbono(abono.getNombreAbono());

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
    public boolean existeFactura(Abono objeto) {
        boolean encontrado = false;
        // Nos fijamos que los datos existan
        if (objeto != null) {
            if (buscarFactura(objeto) != null) {
                encontrado = true;
            }
        }
        return encontrado;
    }

    public Factura buscarFactura(Abono objeto) {
        Factura fact = null;
        try {
            Optional<Factura> faBusqueda = factura.findById(objeto.getIdFactura());

            if (faBusqueda.get().getIdFactura().equals(objeto.getIdFactura())) {
                fact = faBusqueda.get();
            } else {
                throw new Exception("El abono que va a registrar no tiene Factura creada");
            }
        } catch (Exception e) {
            LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
                    + " } fin del error preguntar al Grupo 3 ==> GestorStock");
        }

        return fact;
    }

}
