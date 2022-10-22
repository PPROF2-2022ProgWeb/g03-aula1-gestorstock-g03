import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TiendaCarritoComponent } from './tienda-carrito.component';

describe('TiendaCarritoComponent', () => {
  let component: TiendaCarritoComponent;
  let fixture: ComponentFixture<TiendaCarritoComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TiendaCarritoComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TiendaCarritoComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
