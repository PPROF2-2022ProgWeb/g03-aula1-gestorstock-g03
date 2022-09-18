import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NavlinkComponent } from './navlink.component';

describe('NavlinkComponent', () => {
  let component: NavlinkComponent;
  let fixture: ComponentFixture<NavlinkComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NavlinkComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(NavlinkComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
