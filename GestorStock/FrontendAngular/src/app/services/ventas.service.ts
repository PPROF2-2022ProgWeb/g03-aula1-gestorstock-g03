import { Injectable } from '@angular/core';
import { ProductoCarrito } from '../models/ProductoCarrito';

@Injectable({
  providedIn: 'root'
})
export class VentasService {

  constructor() { }

  doSell(productos: ProductoCarrito[]): void {
    console.log(`Venta finalizada con ${productos.length} productos`);
  }
}
