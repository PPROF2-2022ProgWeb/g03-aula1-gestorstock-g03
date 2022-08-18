import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-dashboard-layout',
  templateUrl: './dashboard-layout.component.html',
  styleUrls: ['./dashboard-layout.component.css']
})
export class DashboardLayoutComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }

  toggleMenu() {
    let menu = document.getElementById('menu');
    let content = document.getElementById('content');
    let docOverflow = document.body.style.overflow;
    if(menu && content){
      menu.classList.toggle('active');
      content.classList.toggle('active');
      document.body.style.overflow = docOverflow === 'hidden' ? '' : 'hidden';
    }
  }
}
