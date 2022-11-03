import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HeroProductComponent } from './hero-product.component';

describe('HeroProductComponent', () => {
  let component: HeroProductComponent;
  let fixture: ComponentFixture<HeroProductComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HeroProductComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HeroProductComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
