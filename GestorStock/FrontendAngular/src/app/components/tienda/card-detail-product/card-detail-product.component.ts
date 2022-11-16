import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { ProductoCarrito } from 'src/app/models/ProductoCarrito';
import { ProductoModel } from 'src/app/models/ProductoModel';
import { CarritoService } from 'src/app/services/carrito.service';
import { NumberInputComponent } from '../../form-controls/number-input/number-input.component';


@Component({
  selector: 'app-card-detail-product',
  templateUrl: './card-detail-product.component.html',
  styleUrls: ['./card-detail-product.component.css']
})
export class CardDetailProductComponent implements OnInit {
  @Input() producto: ProductoModel;
  @ViewChild("cantidad") inputCantidad: NumberInputComponent;

  constructor() { }

  ngOnInit(): void {

  }

  onAddToCartClick(): void {
    CarritoService.agregar(new ProductoCarrito(this.producto, this.inputCantidad.value))
  }

}
