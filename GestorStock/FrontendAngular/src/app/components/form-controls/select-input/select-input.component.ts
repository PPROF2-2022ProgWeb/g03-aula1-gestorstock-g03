import { Component, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { NG_VALUE_ACCESSOR } from '@angular/forms';

@Component({
  selector: 'select-input',
  templateUrl: './select-input.component.html',
  styleUrls: ['./select-input.component.css'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      multi: true,
      useExisting: SelectInputComponent,
    },
  ],
})
export class SelectInputComponent implements OnInit {
  @ViewChild('input') input: HTMLInputElement;

  @Input() title: string = 'Title';
  @Input() disabled: boolean = false;
  @Input() value: any = '';
  @Input() options: {title: string; value: any}[];

  @Output('onChange') change: EventEmitter<any> = new EventEmitter<any>();
  public invalid: boolean = false;
  public touched: boolean = false;
  public isOpen: boolean = false;
  constructor() {
  }

  ngOnInit(): void {}

  onChange = (value: string): void => {};
  onTouched = (): void => {};

  onInput(e: Event): void {
    this.value = (e.target as HTMLInputElement).value;
    this.onChange(this.value);
    this.change.emit(this.value)
  }

  onFocus(): void {
    this.markAsTouched();
  }

  writeValue(value: string): void {
    this.value = value;
  }

  registerOnChange(fn: (value: string) => void): void {
    this.onChange = fn;
  }
  registerOnTouched(fn: () => void): void {
    this.onTouched = fn;
  }

  markAsTouched() {
    if (!this.touched) {
      this.onTouched();
      this.touched = true;
    }
  }

  setDisabledState(isDisabled: boolean): void {
    this.disabled = isDisabled;
  }
}
