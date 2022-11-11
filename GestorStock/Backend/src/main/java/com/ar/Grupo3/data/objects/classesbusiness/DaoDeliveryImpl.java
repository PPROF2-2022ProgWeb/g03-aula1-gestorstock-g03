package com.ar.Grupo3.data.objects.classesbusiness;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.Grupo3.data.objects.interfaces.DaoDeliveryIntf;
import com.ar.Grupo3.data.objects.repositorio.DeliveryRepositorio;
import com.ar.Grupo3.data.objects.repositorio.PedidoRepositorio;
import com.ar.Grupo3.model.Delivery;
import com.ar.Grupo3.model.Pedido;
import com.ar.Grupo3.util.ConvertidorFecha;
import com.ar.Grupo3.util.MensajesObjetos;
import com.ar.Grupo3.viewmodel.DeliveryModel;

@Service
public class DaoDeliveryImpl implements Serializable, DaoDeliveryIntf {

    private static final long serialVersionUID = -4099587390184206309L;

    @Autowired
    private DeliveryRepositorio dao;

    @Autowired
    private PedidoRepositorio pedido;

    @Override
    public Delivery buscar(Long id) {

        Delivery delivery = null;
        try {
            Optional<Delivery> aux = dao.findById(id);
            if (aux.isEmpty()) {
                throw new Exception("El delivery que busca NO EXISTE");
            } else {
                delivery = aux.get();
            }
        } catch (Exception e) {
            LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
                    + " } fin del error preguntar al Grupo 3 ==> GestorStock");
        }

        return delivery;
    }

    @Override
    public DeliveryModel selectPorId(Long id) {

        DeliveryModel delivery = new DeliveryModel();
        try {
            Optional<Delivery> aux = dao.findById(id);
            if (aux.isEmpty()) {
                throw new Exception("El delivery que busca NO EXISTE");
            } else {
                Delivery de = aux.get();

                Pedido del = buscarPedido(de);
                // Completamos el objeto que mostraremos a la vista.

                delivery.setFechaDelivery(de.getFechaDelivery());
                delivery.setIdDelivery(de.getIdDelivery());
                delivery.setIdPedido(de.getIdPedido());
                delivery.setNombreDelivery(de.getNombreDelivery());
                delivery.setObservaciones(de.getObservaciones());

                if (del != null) {
                    delivery.setNombrePedido(del.getNombrePedido());
                } else {
                    delivery.setNombrePedido(MensajesObjetos.PEDIDO_NO_RELACIONADO);
                }

            }
        } catch (Exception e) {
            LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
                    + " } fin del error preguntar al Grupo 3 ==> GestorStock");
        }

        return delivery;
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
    public void agregar(Delivery p) {
        try {
            if (p.getNombreDelivery().isEmpty()) {
                throw new Exception("El Delivery que va a registrar no tiene Nombre");
            }
            if (p.getFechaDelivery() == null) {
                p.setFechaDelivery(ConvertidorFecha.FechaActual());
            }

            dao.save(p);

        } catch (Exception e) {
            LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
                    + " } fin del error preguntar al Grupo 3 ==> GestorStock");
        }
    }

    @Override
    public void modificar(Delivery p) {
        try {
            if (p.getIdDelivery() == null) {
                throw new Exception("El Delivery que va a registrar no tiene identificador de la misma");
            }
            if (p.getIdPedido() == null) {
                throw new Exception("El Delivery que va a registrar no tiene identificador de Pedido");
            }
            if (p.getNombreDelivery().isEmpty()) {
                throw new Exception("El Delivery que va a registrar no tiene Nombre");
            }
            if (p.getFechaDelivery() == null) {
                p.setFechaDelivery(ConvertidorFecha.FechaActual());
            }

            dao.save(p);

        } catch (Exception e) {
            LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
                    + " } fin del error preguntar al Grupo 3 ==> GestorStock");
        }

    }

    @Override
    public void borrar(Delivery p) {
        try {
            if (p.getIdDelivery() == null) {
                throw new Exception("El delivery que va a eliminar no tiene identificador");
            }
            // Revisamos que el pedido exista
            p = buscar(p.getIdDelivery());
            if (p != null) {
                dao.delete(p);
            }
        } catch (Exception e) {
            LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
                    + " } fin del error preguntar al Grupo 3 ==> GestorStock ");
        }

    }

    @Override
    public List<DeliveryModel> SelectTodos() {
        List<DeliveryModel> list = null;
        try {
            List<Delivery> deliverys = dao.findAll();

            // Si la lista no esta vacia
            if (!deliverys.isEmpty()) {

                list = new LinkedList<>();

                // Recorremos la lista
                for (Delivery delivery : deliverys) {
                    // Buscamos id por id
                    Pedido aux = buscarPedido(delivery);

                    //Este es el nuevo objeto que se mostrar para la vista
                    DeliveryModel nuevoObjeto = new DeliveryModel();
                    nuevoObjeto.setFechaDelivery(delivery.getFechaDelivery());
                    nuevoObjeto.setIdDelivery(delivery.getIdDelivery());
                    nuevoObjeto.setIdPedido(delivery.getIdPedido());;
                    nuevoObjeto.setNombreDelivery(delivery.getNombreDelivery());
                    nuevoObjeto.setObservaciones(delivery.getObservaciones());

                    // si existe le asignamos el nombre si no un nombre predeterminado
                    if (aux != null) {
                        nuevoObjeto.setNombrePedido(aux.getNombrePedido());
                    } else {
                        nuevoObjeto.setNombrePedido(MensajesObjetos.PEDIDO_NO_RELACIONADO);
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
    public boolean existePedido(Delivery objeto) {
        boolean encontrado = false;
        // Nos fijamos que los datos existan
        if (objeto != null) {
            if (buscarPedido(objeto) != null) {
                encontrado = true;
            }
        }
        return encontrado;
    }

    public Pedido buscarPedido(Delivery objeto) {
        Pedido fact = null;
        try {
            Optional<Pedido> faBusqueda = pedido.findById(objeto.getIdPedido());

            if (faBusqueda.get().getIdPedido().equals(objeto.getIdPedido())) {
                fact = faBusqueda.get();
            } else {
                throw new Exception("El delivery que va a registrar no tiene Pedido creado");
            }
        } catch (Exception e) {
            LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
                    + " } fin del error preguntar al Grupo 3 ==> GestorStock");
        }

        return fact;
    }

}
