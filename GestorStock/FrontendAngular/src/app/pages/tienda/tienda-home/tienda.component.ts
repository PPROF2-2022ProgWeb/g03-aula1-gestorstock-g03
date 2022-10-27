import { Component, OnInit } from '@angular/core';
import { Productos } from 'src/app/utils/data/productos';

@Component({
  selector: 'app-tienda',
  templateUrl: './tienda.component.html',
  styleUrls: ['./tienda.component.css']
})
export class TiendaComponent implements OnInit {

  public productos = Productos;

  constructor() { }

  ngOnInit(): void {
  }



}
