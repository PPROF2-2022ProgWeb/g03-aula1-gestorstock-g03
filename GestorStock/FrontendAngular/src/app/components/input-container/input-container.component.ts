import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-input-container',
  templateUrl: './input-container.component.html',
  styleUrls: ['./input-container.component.css'],
})
export class InputContainerComponent implements OnInit {
  @Input() title: String = 'Titulo';
  @Input() icon: String = '';
  @Input() labelFor: String = '';
  @Input() errorMessage: String = '';
  @Input() showErrorMessage: String = '';
  constructor() {}

  ngOnInit(): void {}
}
