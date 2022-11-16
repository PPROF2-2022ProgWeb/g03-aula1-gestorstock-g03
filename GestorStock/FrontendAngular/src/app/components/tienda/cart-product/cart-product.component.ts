import { Component, Input, OnInit } from '@angular/core';
import { ProductoCarrito } from 'src/app/models/ProductoCarrito';
import { CarritoService } from 'src/app/services/carrito.service';

@Component({
  selector: 'cart-product',
  templateUrl: './cart-product.component.html',
  styleUrls: ['./cart-product.component.css'],
})
export class CartProductComponent implements OnInit {
  @Input() producto: ProductoCarrito;

  public get total(): number {
    return this.producto.cantidad * this.producto.producto.valor;
  }

  constructor() {}

  ngOnInit(): void {}

  updateCantidad(cantidad: number): void {
    this.producto.cantidad = cantidad;
    CarritoService.editar(this.producto);
  }

  onQuitarClick() {
    CarritoService.eliminar(this.producto);
  }
}
