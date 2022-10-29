import { CurrencyPipe } from '@angular/common';
import {
  Component,
  EventEmitter,
  Input,
  OnInit,
  Output
} from '@angular/core';
import { ICheckoutStatus } from 'src/app/interfaces/checkout';
import { IPlanFinanciacion } from 'src/app/interfaces/planFinanciacion';
import { ProductoCarrito } from 'src/app/models/ProductoCarrito';
import { Iconos } from 'src/app/utils/iconos.enum';

@Component({
  selector: 'ventas-checkout',
  templateUrl: './ventas-checkout.component.html',
  styleUrls: ['./ventas-checkout.component.css'],
})
export class VentasCheckoutComponent implements OnInit {
  @Input() montoVenta: number = 100;
  @Input() products: ProductoCarrito[];
  @Output() onFinish = new EventEmitter<ICheckoutStatus>();
  public icons = Iconos;
  public options = {
    efectivo: 'cash',
    debito: 'debit',
    credito: 'credit',
  };
  public selectedOption: string = this.options.efectivo;

  public planesFinanciacion: IPlanFinanciacion[] = [
    {
      interes: 0,
      cuotas: 1,
    },
    {
      interes: 5,
      cuotas: 3,
    },
    {
      interes: 10,
      cuotas: 6,
    },
    {
      interes: 25,
      cuotas: 12,
    },
  ];

  public get checkoutStatus(): ICheckoutStatus {
    return {
      state: 'pending',
      amount: this.montoVenta,
      products: this.products,
      paymentType: this.selectedOption,
    };
  }

  public montoPago: number = 0;
  public get vuelto(): number {
    return Math.max(this.montoPago - this.montoVenta, 0);
  }
  public recargo: number = 5;
  public planSeleccionado: IPlanFinanciacion = this.planesFinanciacion[0];

  getTotal(interes: number): number {
    return this.montoVenta * (1 + interes / 100);
  }

  getPlan(cuotas: number): IPlanFinanciacion | undefined {
    return this.planesFinanciacion.find((x) => x.cuotas === cuotas);
  }

  getValorCuota(plan: IPlanFinanciacion): number {
    return this.getTotal(plan.interes) / plan.cuotas;
  }

  constructor(public currencyPipe: CurrencyPipe) {}

  ngOnInit(): void {}

  updateMontoPago(montoPago: number) {
    this.montoPago = montoPago;
  }

  updateRecargo(recargo: number) {
    this.recargo = recargo;
  }

  onPlanFinanciacionChanged(value: string) {
    let plan = this.getPlan(Number(value));
    if (plan) {
      this.planSeleccionado = plan;
    }
  }

  onAccept() {
    this.onFinish.emit({
      ...this.checkoutStatus,
      state: 'success',
    });
  }

  onCancel() {
    this.onFinish.emit({
      ...this.checkoutStatus,
      state: 'canceled'
    });
  }
}
