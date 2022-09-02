import { Component, EventEmitter, OnInit, Output } from '@angular/core';

@Component({
  selector: 'app-button',
  templateUrl: './button.component.html',
  styleUrls: ['./button.component.css'],
})
export class ButtonComponent implements OnInit {
  @Output() onClick = new EventEmitter<any>();
  constructor() {}

  ngOnInit(): void {}

  onClickEvent(event: Event): void {
    this.onClick.emit(event);
  }
}
