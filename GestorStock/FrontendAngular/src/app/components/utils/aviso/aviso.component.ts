import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-aviso',
  templateUrl: './aviso.component.html',
  styleUrls: ['./aviso.component.css']
})
export class AvisoComponent implements OnInit {
  
  @Input() titulo: string 
  @Input() mensaje: string 


  constructor() { }

  ngOnInit(): void {
  }

}
