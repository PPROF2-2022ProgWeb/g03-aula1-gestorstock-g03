import { Component, OnInit } from '@angular/core';
import { SearchResult } from 'src/app/interfaces/searchResult';
import { ProductoModel } from 'src/app/models/ProductoModel';
import { ProductosService } from 'src/app/services/productos.service';
import { Iconos } from 'src/app/utils/iconos.enum';

@Component({
  selector: 'app-detalles-producto',
  templateUrl: './detalles-producto.component.html',
  styleUrls: ['./detalles-producto.component.css']
})
export class DetallesProductoComponent implements OnInit {

  public productos: ProductoModel[];
  public iconos = Iconos;

  constructor(private prodServ: ProductosService) { }

  ngOnInit(): void {
    this.prodServ.cargarProductos().subscribe(data => {
      this.productos = data;
      console.log(this.productos);
    })
  }

  onSearchDone(event: SearchResult){
    console.log(event);    
  }

}
