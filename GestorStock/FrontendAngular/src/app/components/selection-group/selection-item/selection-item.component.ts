import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { Iconos } from 'src/app/utils/iconos.enum';

@Component({
  selector: 'selection-item',
  templateUrl: './selection-item.component.html',
  styleUrls: ['./selection-item.component.css']
})
export class SelectionItemComponent implements OnInit {
  @Input() title: string;
  @Input() icon: Iconos;
  @Input() value: string;
  @Input() selected: boolean | string = false;
  @Output() click = new EventEmitter<string>();

  constructor() { }

  ngOnInit(): void {
  }

  onClick(): void {
    this.selected = true;
    this.click.emit(this.value);
  }
}
