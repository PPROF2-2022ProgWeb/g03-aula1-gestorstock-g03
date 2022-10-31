import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FmEdicCartProductComponent } from './fm-editCartProduct.component';

describe('FmUsuarioComponent', () => {
  let component: FmEdicCartProductComponent;
  let fixture: ComponentFixture<FmEdicCartProductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FmEdicCartProductComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FmEdicCartProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
