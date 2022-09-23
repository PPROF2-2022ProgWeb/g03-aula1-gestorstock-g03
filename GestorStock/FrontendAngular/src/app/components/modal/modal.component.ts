import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { IModalResponse, modalStatus } from 'src/app/interfaces/modal';

@Component({
  selector: 'app-modal',
  templateUrl: './modal.component.html',
  styleUrls: ['./modal.component.css'],
})
export class ModalComponent implements OnInit {
  @Input() isOpen: boolean = true;
  @Output() isOpenChange = new EventEmitter<boolean>();
  
  @Output() onClose = new EventEmitter<IModalResponse>();

  constructor() {}

  ngOnInit(): void {}

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
