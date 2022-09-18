import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Iconos } from 'src/app/utils/iconos.enum';

@Component({
  selector: 'app-navlink',
  templateUrl: './navlink.component.html',
  styleUrls: ['./navlink.component.css', '../../../assets/fonts/gs-icofont/style.css']
})
export class NavlinkComponent {
  @Input() to: string = ''
  @Input() icono: Iconos
}
