import { ProductoModel } from './ProductoModel';

export class ProductoCarrito {
  constructor(public producto: ProductoModel, public cantidad: number) {}

  get nombre(): string {
    return this.producto.nombreProducto;
  }

  get precioUnitario(): number {
    return this.producto.valor;
  }

  get total(): number {
    return this.producto.valor * this.cantidad;
  }
}
