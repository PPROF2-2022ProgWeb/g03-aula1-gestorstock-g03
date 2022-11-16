import { Component, Input, OnInit } from '@angular/core';
import { Producto, ProductoModel } from 'src/app/models/ProductoModel';

@Component({
  selector: 'app-product-card',
  templateUrl: './product-card.component.html',
  styleUrls: ['./product-card.component.css']
})
export class ProductCardComponent implements OnInit {
  @Input() product: ProductoModel | Producto;
  
  public get link(): string { 
    return `producto/${this.product.idProducto}`
  }
  constructor() { }

  ngOnInit(): void {
  }

}
