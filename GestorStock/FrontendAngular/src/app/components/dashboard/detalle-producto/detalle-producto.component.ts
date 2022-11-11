import { Component, Input, OnChanges, SimpleChanges } from '@angular/core';
import * as JsBarcode from 'jsbarcode';
import { Producto } from 'src/app/models/ProductoModel';

@Component({
  selector: 'app-detalle-producto',
  templateUrl: './detalle-producto.component.html',
  styleUrls: ['./detalle-producto.component.css'],
})
export class DetalleProductoComponent implements OnChanges {
  @Input() producto: Producto;
  constructor() {}

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['producto']) {
      JsBarcode('#barcode', changes['producto'].currentValue.barCode, {
        fontSize: 16,
        height: 40,
        width: 2
      });
    }
  }
}
