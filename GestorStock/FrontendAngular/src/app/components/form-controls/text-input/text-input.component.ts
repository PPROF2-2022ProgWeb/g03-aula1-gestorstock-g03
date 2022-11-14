import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { AbstractControl, NG_VALIDATORS, NG_VALUE_ACCESSOR, ValidationErrors } from '@angular/forms';
import { Iconos } from 'src/app/utils/iconos.enum';

@Component({
  selector: 'text-input',
  templateUrl: './text-input.component.html',
  styleUrls: ['./text-input.component.css'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      multi: true,
      useExisting: TextInputComponent,
    }
  ],
})
export class TextInputComponent implements OnInit {
  @ViewChild('input') input: HTMLInputElement;

  @Input() title: string = 'Title';
  @Input() disabled: boolean = false;
  @Input() value: string = '';
  @Input() activeColor: string;
  @Input() leadingIcon: Iconos
  @Input() trailingIcon: Iconos
  @Input() errorMessage: string = 'Campo invalido';
  @Input() valid: boolean;
  @Input() type : 'text' | 'password' | 'number' = 'text';
  public touched: boolean = false;

  constructor() {}

  ngOnInit(): void {}

  onChange = (value: string): void => {};
  onTouched = (): void => {};

  onInput(e: Event): void {
    this.value = (e.target as HTMLInputElement).value;
    this.onChange(this.value);
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
