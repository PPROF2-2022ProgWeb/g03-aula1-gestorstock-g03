import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TiendaLayoutsComponent } from './tienda-layouts.component';

describe('TiendaLayoutsComponent', () => {
  let component: TiendaLayoutsComponent;
  let fixture: ComponentFixture<TiendaLayoutsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TiendaLayoutsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TiendaLayoutsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
