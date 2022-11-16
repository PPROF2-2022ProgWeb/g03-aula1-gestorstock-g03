import { Component, OnInit, ViewChild } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { SearchResult } from 'src/app/interfaces/searchResult';
import { ProductoModel } from 'src/app/models/ProductoModel';
import { LoginUser } from 'src/app/models/Usuario.model';
import { CarritoService } from 'src/app/services/carrito.service';
import { Iconos } from 'src/app/utils/iconos.enum';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css'],
})
export class HeaderComponent implements OnInit {
  public productos: ProductoModel[];
  public iconos = Iconos;
  public usuario: LoginUser;
  public isMenuOpen = false;
  public isLoggedIn: boolean;
  public itemsInCart: number = 0;
  public menuPosition = {
    top: '0',
    right: '0',
  };

  @ViewChild('usrButton') usrButton: HTMLElement;

  constructor(private router: Router) {}

  ngOnInit(): void {
    this.usuario = JSON.parse(sessionStorage.getItem('loggedInUser') || 'null');
    if (this.usuario) this.isLoggedIn = true;
    this.itemsInCart = CarritoService.carrito.length
    CarritoService.change.subscribe(() => this.itemsInCart = CarritoService.carrito.length)

    // setInterval(
    //   () => {
    //     let carrito:any[] = JSON.parse(sessionStorage.getItem('carritoTienda') || '[]')
    //     this.itemsInCart = carrito.length
    //   }, 10000
    // )
  }

  onSearchDone(event: SearchResult) {
    this.router.navigate([`tienda/listado/${event.value}`]);
  }

  openMenu(e: Event) {
    this.isMenuOpen = true;
    let element = e.currentTarget as HTMLElement;
    let btRect = element.getBoundingClientRect();
    let right = window.innerWidth - btRect.right;
    console.log(btRect);
    this.menuPosition.right = `${right}px`;
    this.menuPosition.top = `${btRect.bottom}px`;
    console.log(element.querySelector('menu'));
  }

  onMenuContainerClick() {
    this.isMenuOpen = false;
    console.log('click en el menu container');
  }

  cerrarSesion() {
    sessionStorage.removeItem('loggedInUser');
    this.router.navigate(['/']);
  }
}
