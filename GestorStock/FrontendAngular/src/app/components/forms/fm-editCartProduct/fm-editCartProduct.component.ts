import { CurrencyPipe } from '@angular/common';
import {
  Component,
  EventEmitter,
  Input,
  OnChanges,
  OnInit,
  Output,
  SimpleChanges,
} from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ProductoCarrito } from 'src/app/models/ProductoCarrito';
import { Iconos } from 'src/app/utils/iconos.enum';

@Component({
  selector: 'fm-editCartProduct',
  templateUrl: './fm-editCartProduct.component.html',
  styleUrls: ['./fm-editCartProduct.component.css'],
})
export class FmEdicCartProductComponent implements OnChanges {
  @Input() titulo = 'Titulo';
  @Input() productoCarrito: ProductoCarrito;

  @Output('onSubmit') submit = new EventEmitter<ProductoCarrito>();
  @Output('onCancel') cancel = new EventEmitter();
  public Iconos = Iconos;

  public form = new FormGroup({
    producto: new FormControl({ value: '', disabled: true }, [
      Validators.required,
    ]),
    precioUnitario: new FormControl({ value: '', disabled: true }, [
      Validators.required,
    ]),
    unidades: new FormControl(0, [Validators.required]),
    total: new FormControl({ value: '', disabled: true }),
  });

  constructor(private currencyPipe: CurrencyPipe) {}

  ngOnChanges(changes: SimpleChanges): void {
    if (changes['productoCarrito']) {
      this.productoCarrito = changes['productoCarrito'].currentValue;
      if (this.productoCarrito) {
        this.setState(this.productoCarrito);
      }
    }
  }

  setState(productoCarrito: ProductoCarrito) {
    this.form.get('producto')?.setValue(productoCarrito.nombre);
    this.form
      .get('precioUnitario')
      ?.setValue(this.currencyPipe.transform(productoCarrito.precioUnitario));
    this.form.get('unidades')?.setValue(productoCarrito.cantidad);
    this.form
      .get('total')
      ?.setValue(this.currencyPipe.transform(productoCarrito.total));
  }

  onSubmit(): void {
    let fieldUnidades = this.form.get('unidades');
    if (fieldUnidades) {
      let val = Number(fieldUnidades.value);
      this.productoCarrito.cantidad = val;
      this.submit.emit(this.productoCarrito);
      this.reset();
    }
  }

  public reset() {
    if (this.productoCarrito) {
      this.setState(this.productoCarrito);
    } else this.form.reset();
  }

  onCancel() {
    this.cancel.emit();
    this.reset();
  }
}
