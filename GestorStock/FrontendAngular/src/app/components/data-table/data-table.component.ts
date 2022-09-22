import {
  Component,
  OnInit,
  Input,
  ViewChild,
  ElementRef,
  AfterViewInit,
  Output,
  EventEmitter,
} from '@angular/core';
import {
  IDataTableColumn,
  IDataTableSelectedRow,
  IDataTableSelectionChanged,
} from 'src/app/interfaces/dataTable';

@Component({
  selector: 'data-table',
  templateUrl: './data-table.component.html',
  styleUrls: ['./data-table.component.css'],
})
export class DataTableComponent implements OnInit, AfterViewInit {
  @Input() columns: IDataTableColumn[] = [];
  @Input() source: any[];
  @Input() tableStyle: string;

  @Output() selectionChanged = new EventEmitter<IDataTableSelectionChanged>();

  @ViewChild('table') table: ElementRef;
  @ViewChild('body') body: ElementRef;

  private currentSelectedRow: IDataTableSelectedRow;
  private previousSelectedRow: IDataTableSelectedRow;

  constructor() {}

  ngOnInit(): void {
    if (this.columns.length == 0 && this.source.length !== 0) {
      this.columns = Object.keys(this.source[0]).map((x) => ({
        name: x,
        source: x,
      }));
    }
  }

  ngAfterViewInit(): void {
    if (this.table && this.tableStyle) {
      this.tableStyle.includes('bordered') &&
        this.table.nativeElement.classList.add('bordered');
      this.tableStyle.includes('alternated') &&
        this.table.nativeElement.classList.add('alternated');
    }
  }

  selectRow(e: Event, item: any): void {
    let element = e.currentTarget as HTMLElement;
    this.previousSelectedRow = this.currentSelectedRow;
    this.currentSelectedRow = {
      element,
      item,
    };

    this.previousSelectedRow?.element.classList.remove('selected');
    element.classList.add('selected');

    if (this.currentSelectedRow.element !== this.previousSelectedRow?.element) {
      this.selectionChanged.emit({
        previous: this.previousSelectedRow,
        current: this.currentSelectedRow,
      });
    }
  }
}
