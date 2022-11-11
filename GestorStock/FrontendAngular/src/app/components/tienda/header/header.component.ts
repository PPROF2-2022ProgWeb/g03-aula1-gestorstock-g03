import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { Router } from '@angular/router';
import { SearchResult } from 'src/app/interfaces/searchResult';
import { ProductoModel } from 'src/app/models/ProductoModel';
import { LoginService } from 'src/app/services/login.service';
import { ProductosService } from 'src/app/services/productos.service';
import { RegistroService } from 'src/app/services/registro.service';
import { Iconos } from 'src/app/utils/iconos.enum';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  public productos: ProductoModel[];
  public iconos = Iconos;
  
  isLoggedIn: boolean;
  loggedInUser: string;
  permitirRegistro: boolean;

  constructor(private prodServ: ProductosService,
              private logServ: LoginService,
              private regServ: RegistroService,
              private router: Router) { }

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
    this.logServ.getAuth().subscribe((auth: { email: string; }) => {
      if(auth?.email){
        this.isLoggedIn = true;
        this.loggedInUser = auth.email;
        this.permitirRegistro = false;
      }
      else{
        this.isLoggedIn = false;
        this.permitirRegistro = true;
      }
    });
    
    
  }
  

}
