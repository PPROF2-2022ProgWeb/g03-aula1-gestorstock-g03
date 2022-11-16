import { EventEmitter, Injectable, Output } from '@angular/core';
import { ProductoCarrito } from '../models/ProductoCarrito';

@Injectable({
  providedIn: 'root',
})
export class CarritoService {
  private static _carrito: ProductoCarrito[] = [];
  public static get carrito(): ProductoCarrito[] {
    return CarritoService._carrito;
  }
  private static itemName: string = 'carritoTienda';

  public static change = new EventEmitter<ProductoCarrito[]>();

  constructor() {
    CarritoService._carrito = JSON.parse(
      sessionStorage.getItem(CarritoService.itemName) || '[]'
    );
  }

  public static getTotal(): number {
    return CarritoService._carrito.reduce((acc, curr) => acc + (curr.cantidad * curr.producto.valor), 0)

  }

  public static agregar(producto: ProductoCarrito) {
    let i = CarritoService.carrito.findIndex(
      (p) => p.producto.idProducto === producto.producto.idProducto
    );

    if (i >= 0) {
      CarritoService._carrito[i].cantidad += producto.cantidad;
      sessionStorage.setItem(
        CarritoService.itemName,
        JSON.stringify(CarritoService.carrito)
      );
      CarritoService.change.emit(CarritoService.carrito);
      return
    }
    CarritoService._carrito.push(producto);
    sessionStorage.setItem(
      CarritoService.itemName,
      JSON.stringify(CarritoService.carrito)
    );
    CarritoService.change.emit(CarritoService.carrito);
  }

  public static eliminar(producto: ProductoCarrito) {
    let i = CarritoService.carrito.findIndex(
      (p) => p.producto.idProducto === producto.producto.idProducto
    );
    console.log(i);
    CarritoService._carrito.splice(i, 1);
    sessionStorage.setItem(
      CarritoService.itemName,
      JSON.stringify(CarritoService.carrito)
    );
    CarritoService.change.emit(CarritoService.carrito);
  }

  public static editar(producto: ProductoCarrito) {
    let i = CarritoService.carrito.findIndex(
      (p) => p.producto.idProducto === producto.producto.idProducto
    );
    CarritoService._carrito[i] = producto;
    sessionStorage.setItem(
      CarritoService.itemName,
      JSON.stringify(CarritoService.carrito)
    );
    CarritoService.change.emit(CarritoService.carrito);
  }

  public static vaciar() {
    sessionStorage.removeItem(CarritoService.itemName);
  }
}
