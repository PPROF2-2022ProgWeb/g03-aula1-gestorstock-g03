import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashboard-layout',
  templateUrl: './dashboard-layout.component.html',
  styleUrls: ['./dashboard-layout.component.css'],
})
export class DashboardLayoutComponent implements OnInit {
  public isMenuActive: boolean = false;
  public scrollActive: boolean = false;

  public scrollTimeout: any;

  @ViewChild('main') main:ElementRef;

  constructor(private router: Router) {
    router.events.subscribe(() => {
      if (window.innerWidth < 768) this.isMenuActive = false;
    });
  }

  ngOnInit(): void {
  }

  ngAfterViewInit(): void {
    console.log(this.scrollTimeout);
    this.main.nativeElement.addEventListener('scroll', () => {
      this.scrollActive = true;
      if(this.scrollTimeout){
        clearTimeout(this.scrollTimeout);
      }
      this.scrollTimeout = setTimeout(()=>{
        this.scrollActive = false;
      }, 2000)
    });
    // this.main.addEventListener('scroll', this.scrollActive)
  }
  toggleMenu() {
    this.isMenuActive = !this.isMenuActive;
  }
}
