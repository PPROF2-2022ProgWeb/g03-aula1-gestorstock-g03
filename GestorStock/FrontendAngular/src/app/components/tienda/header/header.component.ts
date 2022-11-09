import { Component, OnInit } from '@angular/core';
import { SearchResult } from 'src/app/interfaces/searchResult';
import { ProductoModel } from 'src/app/models/ProductoModel';
import { ProductosService } from 'src/app/services/productos.service';
import { Iconos } from 'src/app/utils/iconos.enum';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  public productos: ProductoModel[];
  public iconos = Iconos;


  constructor(private prodServ: ProductosService) { }

  ngOnInit(): void {
    this.prodServ.getAllProducts().subscribe(data => {
      this.productos = data;
      console.log(this.productos);
    })
  }

  onSearchDone(event: SearchResult){
    console.log(event);    
  }

  loguin(){
    
  }
  


}
