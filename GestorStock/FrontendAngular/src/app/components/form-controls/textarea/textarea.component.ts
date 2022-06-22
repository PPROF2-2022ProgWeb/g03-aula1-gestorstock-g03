import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-textarea',
  templateUrl: './textarea.component.html',
  styleUrls: ['./textarea.component.css']
})
export class TextareaComponent {
  @Input() title: String = 'Titulo';
  @Input() icon: String = '';
  @Input() required: Boolean = false; 
  @Input() placeholder: String = 'Ingrese un valor';
}

