import { AfterViewInit, Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';
import { ModalComponent } from 'src/app/components/modal/modal.component';
import { CarritoService } from 'src/app/services/carrito.service';

declare var paypal: any;
@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.css']
})
export class CheckoutComponent implements OnInit, AfterViewInit {

  constructor(private router: Router) { }

  @ViewChild('paypal', { static: true }) paypalElement: ElementRef;
  @ViewChild('modal') modal: ModalComponent;

  public total: number = 0;
  public static dataAmount = {
    amount: {
      currency_code: 'USD',
      value: 0
    }
  }
  public static modalOpen = false;


  ngAfterViewInit(): void {
    this.modal.onClose.subscribe(() => {
      this.router.navigate(['/tienda'])
    })
  }
  ngOnInit(): void {
    this.total = CarritoService.getTotal();
    CheckoutComponent.dataAmount = {
      amount: {
        currency_code: 'USD',
        value: CarritoService.getTotal()
      }
    }
    CarritoService.change.subscribe(() => {
      this.total = CarritoService.getTotal();
      CheckoutComponent.dataAmount = {
        amount: {
          currency_code: 'USD',
          value: CarritoService.getTotal()
        }
      }
    })

    paypal.Buttons(
      {
        style: {
          color: 'blue',
          shape: 'pill',
          label: 'pay'
        },
        createOrder: function (data: any, actions: any) {
          return actions.order.create({
            purchase_units: [CheckoutComponent.dataAmount]
          })
        },
        onApprove: function (data: any, actions: any) {
          actions.order.capture().then((detalles: any) => {
            console.log(detalles)
            CarritoService.vaciar();
            // window.location.href='/tienda'
          });
        },
        onCancel: function (data: any) {
          alert("Pago cancelado")
          console.log(data)
        }
      }).render('#paypal-button-container')


  }



}