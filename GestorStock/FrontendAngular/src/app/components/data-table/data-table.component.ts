import {
  Component,
  OnInit,
  Input,
  ViewChild,
  ElementRef,
  AfterViewInit,
  Output,
  EventEmitter,
  AfterViewChecked,
} from '@angular/core';
import {
  IDataTableColumn,
  IDataTableEditing,
  IDataTableSelectedRow,
  IDataTableSelectionChanged,
} from 'src/app/interfaces/dataTable';

@Component({
  selector: 'data-table',
  templateUrl: './data-table.component.html',
  styleUrls: ['./data-table.component.css'],
})
export class DataTableComponent
  implements OnInit, AfterViewInit, AfterViewChecked
{
  @Input() columns: IDataTableColumn[] = [];
  @Input() source: any[];
  @Input() tableStyle: string;
  @Input() maxHeight: number = -1;
  @Output() selectionChanged = new EventEmitter<IDataTableSelectionChanged>();

  @ViewChild('table') table: ElementRef;
  @ViewChild('body') body: ElementRef;
  @ViewChild('contenedor') contenedor: ElementRef;
  private currentSelectedRow: IDataTableSelectedRow;
  private previousSelectedRow: IDataTableSelectedRow;
  public editing: IDataTableEditing;
  public isEditing: boolean = false;
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
    this.adjustTableHeight(this.maxHeight);
  }

  ngAfterViewChecked(): void {
    this.adjustTableHeight(this.maxHeight);
  }

  adjustTableHeight(maxHeight: number): void {
    if (this.contenedor?.nativeElement) {
      let contenedor = this.contenedor.nativeElement as HTMLElement;
      let tabla = this.table.nativeElement as HTMLElement;
      let altoTabla = tabla.getBoundingClientRect().height;
      if (maxHeight > 0 && altoTabla > maxHeight) {
        contenedor.style.height = `${maxHeight}px`;
      } else {
        contenedor.style.height = '100%';
      }
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

      if (this.isEditing) {
        this.finishEdition(this.previousSelectedRow.element);
        this.isEditing = false;
      }
    }
  }

  editRow(e: Event, item: any, column: IDataTableColumn) {
    if (!column.editable) return;
    this.isEditing = true;
    let element = e.currentTarget as HTMLElement;
    element.classList.add('editing');
    let value = item[column.source];
    this.editing = {
      item: item,
      prop: column.source,
      prevValue: value,
      newValue: value,
    };
  }

  updateValue(e: Event): void {
    let element = e.target as HTMLInputElement;
    if (this.editing) this.editing.newValue = element.value;
  }

  finishEdition(row: HTMLElement, isAccepted: boolean = true) {
    if (isAccepted) {
      let { item, prop } = this.editing;
      item[prop] = this.editing.newValue;
    }

    row.childNodes.forEach((x) => {
      let child = x as HTMLElement;
      child.classList?.remove('editing');
    });
  }

  cancelEdit() {
    this.finishEdition(this.currentSelectedRow.element, false);
    console.log('cancel');
  }
  acceptEdit() {
    this.finishEdition(this.currentSelectedRow.element, true);
    console.log('accept');
  }
}
