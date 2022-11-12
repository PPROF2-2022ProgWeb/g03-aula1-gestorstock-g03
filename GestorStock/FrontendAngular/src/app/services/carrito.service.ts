import { Injectable } from '@angular/core';
import { ProductoCarrito } from '../models/ProductoCarrito';

@Injectable({
  providedIn: 'root'
})
export class CarritoService {
  private _carrito: ProductoCarrito[] = [];
  public get carrito(): ProductoCarrito[]{
    return this._carrito;
  }

  constructor() {
    this._carrito = JSON.parse(sessionStorage.getItem("carrito") || "[]");
  }

  agregar(){

  }

  eliminar(){

  }

  vaciar(){

  }




}
