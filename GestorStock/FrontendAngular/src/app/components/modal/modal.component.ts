import { AfterViewInit, Component, ElementRef, EventEmitter, Input, OnInit, Output, ViewChild } from '@angular/core';
import { IModalResponse, modalStatus } from 'src/app/interfaces/modal';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css'],
})
export class ModalComponent implements OnInit, AfterViewInit {
  @Input() isOpen: boolean = false;
  @Output() isOpenChange = new EventEmitter<boolean>();
  
  @Output() onClose = new EventEmitter<IModalResponse>();

  @ViewChild('modal') modal: ElementRef;

  constructor() {}

  ngOnInit(): void {}

  ngAfterViewInit(): void {
    this.isOpenChange.emit(this.isOpen);
  }

  public openModal(): void {
    this.isOpen = true;
    this.isOpenChange.emit(this.isOpen);
  }

  closeModal(): void {
    this.isOpen = false;
    this.isOpenChange.emit(this.isOpen);

    this.onClose.emit({
      status: modalStatus.cancel,
    });
  }
}
