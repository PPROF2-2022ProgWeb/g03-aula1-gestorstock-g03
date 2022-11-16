import { Component, OnInit } from '@angular/core';
import { ProductoCarrito } from 'src/app/models/ProductoCarrito';
import { CarritoService } from 'src/app/services/carrito.service';

@Component({
  selector: 'app-tienda-carrito',
  templateUrl: './tienda-carrito.component.html',
  styleUrls: ['./tienda-carrito.component.css'],
})
export class TiendaCarritoComponent implements OnInit {
  public productos: ProductoCarrito[] = [];
  constructor() {}
  public total: number = 0;

  ngOnInit(): void {
    this.productos = CarritoService.carrito;
    this.total = CarritoService.getTotal();

    CarritoService.change.subscribe((data) => {
      this.productos = data
      this.total = CarritoService.getTotal();
    });
  }
}
