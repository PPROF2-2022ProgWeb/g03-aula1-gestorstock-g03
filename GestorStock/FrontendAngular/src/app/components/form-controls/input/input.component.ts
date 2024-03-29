import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-input',
  templateUrl: './input.component.html',
  styleUrls: ['./input.component.css']
})
export class InputComponent implements OnInit{
  @Input() title: String = 'Titulo';
  @Input() icon: String = '';
  @Input() required: Boolean = false; 
  @Input() placeholder: String = 'Ingrese un valor';
  @Input() type: String = 'type';
  @Input() name: String = '';
  @Input() id: String = '';
  

  constructor(){}


  ngOnInit() {
    
  }


}
