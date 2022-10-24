import {
  Component,
  Input,
  OnChanges,
  OnInit,
  SimpleChanges,
  ViewChild,
} from '@angular/core';
import { NG_VALUE_ACCESSOR } from '@angular/forms';

@Component({
  selector: 'number-input',
  templateUrl: './number-input.component.html',
  styleUrls: ['./number-input.component.css'],
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      multi: true,
      useExisting: NumberInputComponent,
    },
  ],
})
export class NumberInputComponent {
  @ViewChild('input') input: HTMLInputElement;

  @Input() title: string;
  @Input() disabled: boolean = false;
  @Input() value: number = 0;
  @Input() color: string = '#00324a';
  @Input() step: number = 1;
  @Input() min: number | string;
  @Input() max: number | string;

  public invalid: boolean = false;
  public touched: boolean = false;

  private multiplier: number = 1;
  private stepDelay: number = 500;
  private stepTimer: any;

  constructor() {
    window.addEventListener('keydown', (event: KeyboardEvent) => {
      if (event.shiftKey) this.multiplier = 10;
      if (event.ctrlKey) this.multiplier = 100;
    });
    window.addEventListener('keyup', (event: KeyboardEvent) => {
      if (event.key === 'Shift' || event.key === 'Control') this.multiplier = 1;
    });
  }
  stepUp(): void {
    if (typeof this.value === 'string') {
      this.value = Number(this.value);
    }
    this.value += this.step * this.multiplier;
    if (this.max && this.value > (this.max as number)) {
      this.value = Number(this.max);
    }
  }
  stepDown(): void {
    if (typeof this.value === 'string') {
      this.value = Number(this.value);
    }
    this.value -= this.step * this.multiplier;
    if (this.min && this.value < (this.min as number)) {
      this.value = Number(this.min);
    }
  }

  stepper(direction: 'up' | 'down'): void {
    direction === 'up' && this.stepUp();
    direction === 'down' && this.stepDown();
    if (this.stepDelay > 50) this.stepDelay -= 50;
    this.stepTimer = setTimeout(() => {
      this.stepper(direction);
    }, this.stepDelay);
  }

  stopStepper(): void {
    clearTimeout(this.stepTimer);
    this.stepDelay = 500;
  }

  onStepUp(): void {
    this.stepper('up');
  }

  onStepDown(): void {
    this.stepper('down');
  }

  onMouseWheel(e: Event): void {
    e.preventDefault();
    if(this.disabled) return;
    const { deltaY } = e as WheelEvent;
    if (deltaY > 0) this.stepDown();
    if (deltaY < 0) this.stepUp();
  }

  onChange = (value: number): void => {};
  onTouched = (): void => {};

  onInput(e: Event): void {
    this.value = (e.target as HTMLInputElement).valueAsNumber;
    this.onChange(this.value);
  }

  onFocus(): void {
    this.markAsTouched();
  }

  writeValue(value: number): void {
    this.value = value;
  }

  registerOnChange(fn: (value: number) => void): void {
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
