import { Component, OnInit } from '@angular/core';
import { Iconos } from 'src/app/utils/iconos.enum';

@Component({
  selector: 'app-categories',
  templateUrl: './categories.component.html',
  styleUrls: ['./categories.component.css']
})
export class CategoriesComponent implements OnInit {
  public Iconos = Iconos
  constructor() { }

  ngOnInit(): void {
  }

}
