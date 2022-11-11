import { Component, OnInit } from '@angular/core';
import { ProductoModel } from 'src/app/models/ProductoModel';
import { ProductosService } from 'src/app/services/productos.service';

@Component({
  selector: 'app-tienda',
  templateUrl: './tienda-home.component.html',
  styleUrls: ['./tienda-home.component.css']
})
export class TiendaComponent implements OnInit {
  producto: ProductoModel[] = [];


  constructor(private prodService: ProductosService,
              ) {}

  ngOnInit(): void {
  }

 
}
