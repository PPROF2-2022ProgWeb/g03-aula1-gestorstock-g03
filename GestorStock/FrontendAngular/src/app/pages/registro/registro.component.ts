import { Component, OnInit } from '@angular/core';
import { Iconos } from 'src/app/utils/iconos.enum';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})
export class RegistroComponent implements OnInit {
  icon = Iconos;
  constructor() { }

  ngOnInit(): void {
  }



}
