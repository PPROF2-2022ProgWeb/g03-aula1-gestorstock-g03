import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DataCounterComponent } from './data-counter.component';

describe('DataCounterComponent', () => {
  let component: DataCounterComponent;
  let fixture: ComponentFixture<DataCounterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DataCounterComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DataCounterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
