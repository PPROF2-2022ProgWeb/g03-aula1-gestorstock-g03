import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CardDetailProductComponent } from './card-detail-product.component';

describe('CardDetailProductComponent', () => {
  let component: CardDetailProductComponent;
  let fixture: ComponentFixture<CardDetailProductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CardDetailProductComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CardDetailProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
