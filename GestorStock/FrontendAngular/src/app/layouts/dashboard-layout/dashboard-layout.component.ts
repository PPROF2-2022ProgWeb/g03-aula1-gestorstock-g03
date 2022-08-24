import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard-layout',
  templateUrl: './dashboard-layout.component.html',
  styleUrls: ['./dashboard-layout.component.css']
})
export class DashboardLayoutComponent implements OnInit {

  public isMenuActive: boolean = false;
  constructor() { }

  ngOnInit(): void {
  }

  toggleMenu() {
    this.isMenuActive = !this.isMenuActive;
  }
}
