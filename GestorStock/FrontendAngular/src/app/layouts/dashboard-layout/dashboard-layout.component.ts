import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard-layout',
  templateUrl: './dashboard-layout.component.html',
  styleUrls: ['./dashboard-layout.component.css'],
})
export class DashboardLayoutComponent implements OnInit {
  public isMenuActive: boolean = false;

  constructor(private router: Router) {
    router.events.subscribe(() => {
      if (window.innerWidth < 768) this.isMenuActive = false;
    });
  }

  ngOnInit(): void {}

  toggleMenu() {
    this.isMenuActive = !this.isMenuActive;
  }
}
