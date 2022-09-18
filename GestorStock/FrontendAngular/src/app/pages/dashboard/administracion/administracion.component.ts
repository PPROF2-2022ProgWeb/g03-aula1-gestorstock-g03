import { Component, OnInit } from '@angular/core';
import { Iconos } from 'src/app/utils/iconos.enum';

@Component({
  selector: 'app-administracion',
  templateUrl: './administracion.component.html',
  styleUrls: ['./administracion.component.css']
})
export class AdministracionComponent implements OnInit {
  public icons = Iconos

  public ventas:number = 1253;
  public ingresosTurno:number = 10432;
  public gananciasTurno:number = 10432;

  constructor() { }

  ngOnInit(): void {
  }

}
