import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { SearchResult } from 'src/app/interfaces/searchResult';
import { ProductoModel } from 'src/app/models/ProductoModel';
import { ProductosService } from 'src/app/services/productos.service';
import { Iconos } from 'src/app/utils/iconos.enum';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  public productos: ProductoModel[];
  public iconos = Iconos;

  isLoggedIn: boolean;
  loggedInUser: string;
  permitirRegistro: boolean;

  constructor(
    private router: Router
  ) {}

  ngOnInit(): void {}

  onSearchDone(event: SearchResult) {
    console.log(event);
    this.router.navigate([`tienda/listado/${event.value}`])
  }
}
