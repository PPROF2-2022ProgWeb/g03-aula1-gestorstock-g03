import {
  AfterContentInit,
  Component,
  ContentChildren,
  Input,
  OnChanges,
  Output,
  QueryList,
  SimpleChanges,
  EventEmitter
} from '@angular/core';
import { ignoreElements } from 'rxjs';
import { SelectionItemComponent } from './selection-item/selection-item.component';

@Component({
  selector: 'selection-group',
  templateUrl: './selection-group.component.html',
  styleUrls: ['./selection-group.component.css'],
})
export class SelectionGroupComponent implements AfterContentInit, OnChanges {
  @ContentChildren(SelectionItemComponent)
  options!: QueryList<SelectionItemComponent>;

  @Input() selectedValue: string;
  @Output() selectedValueChange = new EventEmitter<string>();
  @Output() selectionChanged = new EventEmitter<string>();

  private prevValue: string;
  private viewInited: boolean = false;
  constructor() {}

  ngAfterContentInit(): void {
    this.viewInited;
    let conter = 0;
    this.options.forEach((x: SelectionItemComponent) => {
      x.click.subscribe((x) => {
        this.changeSelectedValue(x);
      });
      if (!x.value) {
        x.value = `${x.title}-${conter}`;
        conter++;
      }
    });

    this.selectedValue && this.changeSelectedValue(this.selectedValue)
  }

  ngOnChanges(changes: SimpleChanges): void {
    if(changes['selectedValue'] && this.viewInited){
      let value = changes['selectedValue'].currentValue;
      this.changeSelectedValue(value)
    }
  }

  changeSelectedValue(value: string): void {
    this.prevValue = this.selectedValue;
    this.options?.forEach((x) => {
      x.selected = x.value === value;
    });
    this.selectedValue = value;
    this.selectionChanged.emit(value)
    this.selectedValueChange.emit(this.selectedValue);
  }
}
