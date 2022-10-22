import { Producto } from './Producto';

export class ProductoCarrito {
  constructor(public producto: Producto, public cantidad: number) {}

  get nombre(): string {
    return this.producto.name;
  }

  get precioUnitario(): number {
    return this.producto.price;
  }

  get total(): number {
    return this.producto.price * this.cantidad;
  }
}
