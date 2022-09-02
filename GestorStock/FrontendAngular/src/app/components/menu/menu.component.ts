import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Iconos } from 'src/app/utils/iconos.enum';

@Component({
  selector: 'app-menu',
  templateUrl: './menu.component.html',
  styleUrls: ['./menu.component.css']
})
export class MenuComponent implements OnInit {
  public iconos = Iconos

  constructor(private router: Router) {}

  ngOnInit(): void {
  }

  closeSesion(): void {
    this.router.navigate(['/'])
  }
}
