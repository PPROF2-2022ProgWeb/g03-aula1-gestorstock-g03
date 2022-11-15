package com.ar.Grupo3.data.factory;

import java.io.Serializable;

import com.ar.Grupo3.data.objects.classesbusiness.*;
import com.ar.Grupo3.data.objects.interfaces.*;
import com.ar.Grupo3.seguridad.negocio.DaoRolImpl;
import com.ar.Grupo3.seguridad.negocio.DaoUsuarioImpl;

public class FabricaDAO implements Serializable {

    private static final long serialVersionUID = -93933310251647313L;

    //Para generar instancia de Usuario
    private static DaoUsuarioIntf usuario;

    public static DaoUsuarioIntf obtenerUsuario() {
        if (usuario == null) {
            usuario = new DaoUsuarioImpl();
        }
        return usuario;
    }

    //Para generar instancia de Abono
    private static DaoAbonoIntf abono;

    public static DaoAbonoIntf obtenerAbono() {
        if (abono == null) {
            abono = new DaoAbonoImpl();
        }
        return abono;
    }

    //Para generar instancia de Provincia
    private static DaoProvinciaIntf provincia;

    public static DaoProvinciaIntf obtenerProvincia() {
        if (provincia == null) {
            provincia = new DaoProvinciaImpl();
        }
        return provincia;
    }

    //Para generar instancia de Delivery
    private static DaoDeliveryIntf delivery;

    public static DaoDeliveryIntf obtenerDelivery() {
        if (delivery == null) {
            delivery = new DaoDeliveryImpl();
        }
        return delivery;
    }

    //Para generar instancia de Departamento
    private static DaoDepartamentoIntf departamento;

    public static DaoDepartamentoIntf obtenerDepartamento() {
        if (departamento == null) {
            departamento = new DaoDepartamentoImpl();
        }
        return departamento;
    }

    //Para generar instancia de Factura
    private static DaoFacturaIntf factura;

    public static DaoFacturaIntf obtenerFactura() {
        if (factura == null) {
            factura = new DaoFacturaImpl();
        }
        return factura;
    }

    //Para generar instancia de Pedido
    private static DaoPedidoIntf pedido;

    public static DaoPedidoIntf obtenerPedido() {
        if (pedido == null) {
            pedido = new DaoPedidoImpl();
        }
        return pedido;
    }

    //Para generar instancia de Producto
    private static DaoProductoIntf producto;

    public static DaoProductoIntf obtenerProducto() {
        if (producto == null) {
            producto = new DaoProductoImpl();
        }
        return producto;
    }

    //Para generar instancia de Servicio
    private static DaoServicioIntf servicio;

    public static DaoServicioIntf obtenerServicio() {
        if (servicio == null) {
            servicio = new DaoServicioImpl();
        }
        return servicio;
    }

    //Para generar instancia de TipoProducto
    private static DaoTipoProductoIntf tipoProducto;

    public static DaoTipoProductoIntf obtenerTipoProducto() {
        if (tipoProducto == null) {
            tipoProducto = new DaoTipoProductoImpl();
        }
        return tipoProducto;
    }

    //Para generar instancia de Ventas
    private static DaoVentasIntf ventas;

    public static DaoVentasIntf obtenerVentas() {
        if (ventas == null) {
            ventas = new DaoVentasImpl();
        }
        return ventas;
    }

    //Para generar instancia de Rol
    private static DaoRolIntf rol;

    public static DaoRolIntf obtenerRol() {
        if (rol == null) {
            rol = new DaoRolImpl();
        }
        return rol;
    }

}
