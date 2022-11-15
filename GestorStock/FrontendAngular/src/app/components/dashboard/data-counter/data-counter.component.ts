import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-data-counter',
  templateUrl: './data-counter.component.html',
  styleUrls: ['./data-counter.component.css']
})
export class DataCounterComponent implements OnInit {
  @Input() icono: string;
  @Input() titulo: string = "titulo";
  constructor() { }

  ngOnInit(): void {
  }

}
