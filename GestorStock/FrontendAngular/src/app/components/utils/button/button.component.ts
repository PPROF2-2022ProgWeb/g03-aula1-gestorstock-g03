import { AfterViewInit, Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';

@Component({
  selector: 'app-button',
  templateUrl: './button.component.html',
  styleUrls: ['./button.component.css'],
})
export class ButtonComponent implements OnInit, AfterViewInit {

  @Input() bordered: string;
  @Input() inverted: string;
  @Input() square: string;
  @Input() centered: string;
  @Input() disabled: boolean;
  
  @Output() onClick = new EventEmitter<any>();

  @ViewChild("button") button:ElementRef;
  constructor() {}

  ngOnInit(): void {
  }

  ngAfterViewInit(): void {
    if (this.bordered !== undefined) {
      this.button.nativeElement.classList.add("bordered")
    }
    if (this.inverted !== undefined) {
      this.button.nativeElement.classList.add("inverted")
    }
    if (this.square !== undefined) {
      this.button.nativeElement.classList.add("square")
    }
    if (this.centered !== undefined) {
      this.button.nativeElement.classList.add("centered")
    }
  }
  
  onClickEvent(event: Event): void {
    this.onClick.emit(event);
  }
}
