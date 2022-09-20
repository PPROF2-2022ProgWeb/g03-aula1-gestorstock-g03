import { Component, OnInit, Input, ViewChild, ElementRef, AfterViewInit } from '@angular/core';
import { dataTableColumn } from 'src/app/interfaces/dataTableColumn';

@Component({
  selector: 'data-table',
  templateUrl: './data-table.component.html',
  styleUrls: ['./data-table.component.css'],
})
export class DataTableComponent implements OnInit, AfterViewInit {
  @Input() columns: dataTableColumn[] = [];
  @Input() source: any[];
  @Input() tableStyle: string;

  @ViewChild('table') table: ElementRef;
  constructor() {}

  ngOnInit(): void {
    if (this.columns.length == 0) {
      this.columns = Object.keys(this.source[0]).map((x) => ({
        name: x,
        source: x,
      }));
    }
  }

  ngAfterViewInit(): void {
    if (this.table && this.tableStyle) {
      console.log(this.tableStyle)
      this.tableStyle.includes('bordered') &&
        this.table.nativeElement.classList.add('bordered');
      this.tableStyle.includes('alternated') &&
        this.table.nativeElement.classList.add('alternated');
    }
  }
}
