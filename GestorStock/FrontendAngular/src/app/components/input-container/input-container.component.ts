import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-input-container',
  templateUrl: './input-container.component.html',
  styleUrls: ['./input-container.component.css'],
})
export class InputContainerComponent implements OnInit {
  @Input() title: string = 'Titulo';
  @Input() icon: string = '';
  @Input() labelFor: string = '';
  @Input() errorMessage: string = '';
  @Input() showErrorMessage: boolean;
  constructor() {}

  ngOnInit(): void {}
}
