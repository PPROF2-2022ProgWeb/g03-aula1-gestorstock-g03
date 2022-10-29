import { ComponentFixture, TestBed } from '@angular/core/testing';

import { VentasCheckoutComponent } from './ventas-checkout.component';

describe('VentasCheckoutComponent', () => {
  let component: VentasCheckoutComponent;
  let fixture: ComponentFixture<VentasCheckoutComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ VentasCheckoutComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(VentasCheckoutComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
