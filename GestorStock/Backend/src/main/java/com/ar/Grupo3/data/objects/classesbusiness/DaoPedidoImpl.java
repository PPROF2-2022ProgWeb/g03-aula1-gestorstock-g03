package com.ar.Grupo3.data.objects.classesbusiness;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.Grupo3.data.objects.interfaces.DaoPedidoIntf;
import com.ar.Grupo3.data.objects.repositorio.*;
import com.ar.Grupo3.model.*;
import com.ar.Grupo3.seguridad.entity.Usuario;
import com.ar.Grupo3.seguridad.repositorios.UsuarioRepositorio;
import com.ar.Grupo3.util.MensajesObjetos;
import com.ar.Grupo3.viewmodel.PedidoModel;

@Service
public class DaoPedidoImpl implements Serializable, DaoPedidoIntf {

	private static final long serialVersionUID = -1058037071478689645L;

	@Autowired
	private PedidoRepositorio dao;

	@Autowired
	private FacturaRepositorio factura;

	@Autowired
	private ProductoRepositorio producto;

	@Autowired
	private UsuarioRepositorio usuario;

	@Override
	public Pedido buscar(Long id) {

		Pedido pedido = null;
		try {
			Optional<Pedido> aux = dao.findById(id);
			if (aux.isEmpty()) {
				throw new Exception("El pedido que busca NO EXISTE");
			} else {
				pedido = aux.get();
			}
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}

		return pedido;
	}
	
	@Override
	public PedidoModel selectPorId(Long id) {

		PedidoModel pedido = new PedidoModel();
		try {
			Optional<Pedido> aux = dao.findById(id);
			if (aux.isEmpty()) {
				throw new Exception("El pedido que busca NO EXISTE");
			} else {
				Pedido obj = aux.get();

				Factura fac = buscarFactura(obj);
				Usuario usu = buscarUsuario(obj);
				Producto pro = buscarProducto(obj);
				// Completamos el objeto que mostraremos a la vista.
				pedido.setFechaPedido(obj.getFechaPedido());
				pedido.setIdPedido(obj.getIdPedido());
				pedido.setIdFactura(obj.getIdFactura());
				pedido.setIdProducto(obj.getIdProducto());
				pedido.setIdUsuario(obj.getIdUsuario());
				pedido.setObservaciones(obj.getObservaciones());
				pedido.setNombreFactura(obj.getNombrePedido());
				pedido.setValor(obj.getValor());
				pedido.setNombrePedido(obj.getNombrePedido());

				// si existe le asignamos el nombre si no un nombre predeterminado
				if (fac != null) {
					pedido.setNombreFactura(fac.getNombreFactura());
				} else {
					pedido.setNombreFactura(MensajesObjetos.FACTURA_NO_RELACIONADA);
				}

				if (usu != null) {
					pedido.setNombreUsuario(usu.getUsername());
				} else {
					pedido.setNombreUsuario(MensajesObjetos.USUARIO_NO_RELACIONADO);
				}
				
				if (pro != null) {
					pedido.setNombreProducto(pro.getNombreProducto());
				} else {
					pedido.setNombreProducto(MensajesObjetos.PRODUCTO_NO_RELACIONADO);
				}
				
			}
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}

		return pedido;
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
	public void agregar(Pedido p) {
		try {
			if (p.getNombrePedido().isEmpty()) {
				throw new Exception("La Pedido que va a registrar no tiene Nombre");
			}

			dao.save(p);

		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}
	}

	@Override
	public void modificar(Pedido p) {
		try {
			if (p.getIdPedido() == null) {
				throw new Exception("El Pedido que va a registrar no tiene identificador del mismo");
			}
			if (p.getNombrePedido().isEmpty()) {
				throw new Exception("La Pedido que va a registrar no tiene Nombre");
			}

			dao.save(p);

		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}

	}

	@Override
	public void borrar(Pedido p) {
		try {
			if (p.getIdPedido() == null) {
				throw new Exception("El pedido a eliminar no tiene identificador");
			}
			// Revisamos que El Pedido exista
			p = buscar(p.getIdPedido());
			if (p != null) {
				dao.delete(p);
			}

		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock ");
		}
	}

	@Override
	public List<PedidoModel> SelectTodos() {
		List<PedidoModel> list = null;
		try {
			List<Pedido> pedidos = dao.findAll();

			// Si la lista no esta vacia
			if (!pedidos.isEmpty()) {

				list = new LinkedList<>();

				// Recorremos la lista
				for (Pedido pedido : pedidos) {
					// Buscamos id por id
					Factura fac = buscarFactura(pedido);
					Usuario usu = buscarUsuario(pedido);
					Producto pro = buscarProducto(pedido);

					//Este es el nuevo objeto que se mostrar para la vista
					PedidoModel nuevoObjeto = new PedidoModel();
					nuevoObjeto.setFechaPedido(pedido.getFechaPedido());
					nuevoObjeto.setIdPedido(pedido.getIdPedido());
					nuevoObjeto.setIdFactura(pedido.getIdFactura());
					nuevoObjeto.setIdProducto(pedido.getIdProducto());
					nuevoObjeto.setIdUsuario(pedido.getIdUsuario());
					nuevoObjeto.setObservaciones(pedido.getObservaciones());
					nuevoObjeto.setNombrePedido(pedido.getNombrePedido());
					nuevoObjeto.setValor(pedido.getValor());

					// si existe le asignamos el nombre si no un nombre predeterminado
					if (fac != null) {
						nuevoObjeto.setNombreFactura(fac.getNombreFactura());
					} else {
						nuevoObjeto.setNombreFactura(MensajesObjetos.FACTURA_NO_RELACIONADA);
					}

					if (usu != null) {
						nuevoObjeto.setNombreUsuario(usu.getUsername());
					} else {
						nuevoObjeto.setNombreUsuario(MensajesObjetos.USUARIO_NO_RELACIONADO);
					}
					
					if (pro != null) {
						nuevoObjeto.setNombreProducto(pro.getNombreProducto());
					} else {
						nuevoObjeto.setNombreProducto(MensajesObjetos.PRODUCTO_NO_RELACIONADO);
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
	public boolean existeFactura(Pedido objeto) {
		boolean encontrado = false;
		// Nos fijamos que los datos existan
		if (objeto != null) {
			if (buscarFactura(objeto) != null) {
				encontrado = true;
			}
		}
		return encontrado;
	}

	public boolean existeProducto(Pedido objeto) {
		boolean encontrado = false;
		// Nos fijamos que los datos existan
		if (objeto != null) {
			if (buscarProducto(objeto) != null) {
				encontrado = true;
			}
		}
		return encontrado;
	}
	
	public boolean existeUsuario(Pedido objeto) {
		boolean encontrado = false;
		// Nos fijamos que los datos existan
		if (objeto != null) {
			if (buscarUsuario(objeto) != null) {
				encontrado = true;
			}
		}
		return encontrado;
	}
	
	public Factura buscarFactura(Pedido objeto) {
		Factura fact = null;
		try {
			Optional<Factura> faBusqueda = factura.findById(objeto.getIdFactura());

			if (faBusqueda.get().getIdFactura().equals(objeto.getIdFactura())) {
				fact = faBusqueda.get();
			} else {
				throw new Exception("El pedido que va a registrar no tiene Factura creada");
			}
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
					+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}

		return fact;
	}
	public Producto buscarProducto(Pedido objeto) {
		Producto fact = null;
		try {
			Optional<Producto> faBusqueda = producto.findById(objeto.getIdProducto());
			
			if (faBusqueda.get().getIdProducto().equals(objeto.getIdProducto())) {
				fact = faBusqueda.get();
			} else {
				throw new Exception("El pedido que va a registrar no tiene Producto creada");
			}
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
			+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}
		
		return fact;
	}
	public Usuario buscarUsuario(Pedido objeto) {
		Usuario fact = null;
		try {
			Optional<Usuario> faBusqueda = usuario.findById(objeto.getIdUsuario());
			
			if (faBusqueda.get().getIdUsuario().equals(objeto.getIdUsuario())) {
				fact = faBusqueda.get();
			} else {
				throw new Exception("El pedido que va a registrar no tiene Usuario creada");
			}
		} catch (Exception e) {
			LogManager.getLogger("Un error ha ocurrido: -> { " + e.getMessage()
			+ " } fin del error preguntar al Grupo 3 ==> GestorStock");
		}
		
		return fact;
	}

}
