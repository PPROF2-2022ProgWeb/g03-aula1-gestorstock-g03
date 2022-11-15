import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FmProductosComponent } from './fm-productos.component';

describe('FcProductosComponent', () => {
  let component: FmProductosComponent;
  let fixture: ComponentFixture<FmProductosComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FmProductosComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FmProductosComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
