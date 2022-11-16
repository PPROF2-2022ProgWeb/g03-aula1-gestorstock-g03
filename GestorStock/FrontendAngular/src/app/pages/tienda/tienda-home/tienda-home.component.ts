import { Component, OnInit } from '@angular/core';
import { ProductoModel } from 'src/app/models/ProductoModel';
import { ProductosService } from 'src/app/services/productos.service';

@Component({
  selector: 'app-tienda',
  templateUrl: './tienda-home.component.html',
  styleUrls: ['./tienda-home.component.css'],
})
export class TiendaComponent implements OnInit {
  productos: ProductoModel[] = [];

  constructor(private prodService: ProductosService) {}

  ngOnInit(): void {
    this.prodService.cargarProductos().subscribe(data => {
      let cantidad = Math.min(10, data.length)
      for (let i = 0; i < cantidad; i++) {
        let index = Math.floor(Math.random() * cantidad)
        this.productos.push(data[index])
      }
    })
  }
}
