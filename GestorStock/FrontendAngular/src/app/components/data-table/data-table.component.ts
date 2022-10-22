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
  OnChanges,
  SimpleChanges,
} from '@angular/core';
import {
  IDataTableColumn,
  IDataTableEditionFinished,
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
  implements OnInit, AfterViewInit, AfterViewChecked, OnChanges
{
  @Input() columns: IDataTableColumn[] = [];
  @Input() source: any[];
  @Input() tableStyle: string;
  @Input() maxHeight: number = -1;
  @Output() selectionChanged = new EventEmitter<IDataTableSelectionChanged>();
  @Output() editionFinished = new EventEmitter<IDataTableEditionFinished>();
  @Output() selectedItem: any;
  @Output() selectedIndex: number;

  @ViewChild('table') table: ElementRef;
  @ViewChild('body') body: ElementRef;
  @ViewChild('tableContainer') tableContainer: ElementRef;
  @ViewChild('contenedor') contenedor: ElementRef;
  @ViewChild('tableEventShifter') tableEventShifter: ElementRef;

  private isSelectionMoved: boolean = false;
  private currentSelectedRow?: IDataTableSelectedRow;
  private previousSelectedRow?: IDataTableSelectedRow;
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

  ngOnChanges(changes: SimpleChanges): void {
    if(changes['source']){
      this.deselectRow();
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
    if(this.editing){
      let input = this.editingElement.childNodes[1] as HTMLInputElement;
      input.focus && input.focus();
    }
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

  scrollToRow(row: HTMLElement): void {
    let thead = (this.table.nativeElement as HTMLElement)
      .childNodes[0] as HTMLElement;
    let theadRect = thead.getBoundingClientRect();
    let contenedor = this.contenedor.nativeElement as HTMLElement;
    let contenedorRect = contenedor.getBoundingClientRect();
    let rowRect = row.getBoundingClientRect();
    let diff = 0;

    if (rowRect.top < contenedorRect.top + theadRect.height) {
      diff =
        contenedor.scrollTop -
        (contenedorRect.top - rowRect.top) -
        theadRect.height;
      console.log(`
      contTop: ${contenedorRect.top}
      rowTop: ${rowRect.top}
      diff: ${diff}
      scroll: ${contenedor.scrollTop}
      `);
      contenedor.scroll(0, diff);
    } else if (rowRect.bottom > contenedorRect.bottom) {
      diff = contenedor.scrollTop - (contenedorRect.bottom - rowRect.bottom);
      console.log(`
      contTop: ${contenedorRect.top}
      rowTop: ${rowRect.top}
      diff: ${diff}
      scroll: ${contenedor.scrollTop}
      `);
      contenedor.scroll(0, diff);
    }
  }

  deselectRow(){
    this.currentSelectedRow?.element.classList?.remove('selected');
    this.selectedIndex = -1;
    this.selectedItem = undefined;
  }

  selectRow(row: HTMLElement, item: any): void {
    this.previousSelectedRow = this.currentSelectedRow;
    this.currentSelectedRow = {
      element: row,
      item,
    };

    this.scrollToRow(row);

    this.contenedor.nativeElement.scrollTo(row);

    this.previousSelectedRow?.element.classList?.remove('selected');

    row.classList?.add('selected');

    if (this.currentSelectedRow.element !== this.previousSelectedRow?.element) {
      this.selectedIndex = this.source.findIndex(
        (x) => x === this.currentSelectedRow?.item
      );
      this.selectedItem = this.source[this.selectedIndex];
      this.selectionChanged.emit({
        previous: this.previousSelectedRow,
        current: this.currentSelectedRow,
      });

      if (this.isEditing && this.previousSelectedRow) {
        this.finishEdition(this.previousSelectedRow.element);
        this.isEditing = false;
      }
    }
  }

  onSelectRow(e: Event, item: any): void {
    this.selectRow(e.currentTarget as HTMLElement, item);
  }

  private editingElement: HTMLElement;
  editRow(e: Event, item: any, column: IDataTableColumn) {
    if (!column.editable) return;
    this.isEditing = true;
    let element = e.currentTarget as HTMLElement;
    this.editingElement = element;
    element.classList.add('editing');
    let input = element.childNodes[1] as HTMLInputElement;
    input.value = item[column.source];
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
    let { item, prop } = this.editing;
    let state = ''
    if (isAccepted) {
      item[prop] = this.editing.newValue;
      this.editionFinished.emit({
        ...this.editing,
        state: 'accepted'
      })
    }else {
      state = 'rejected'
      item[prop] = this.editing.prevValue;
      this.editionFinished.emit({
        ...this.editing,
        state: 'rejected'
      })
    }

    row.childNodes.forEach((x) => {
      let child = x as HTMLElement;
      child.classList?.remove('editing');
    });

    
  }

  cancelEdit() {
    this.currentSelectedRow && this.isEditing && this.finishEdition(this.currentSelectedRow.element, false);
    this.isEditing = false;
  }
  acceptEdit() {
    this.currentSelectedRow && this.isEditing && this.finishEdition(this.currentSelectedRow.element, true);
    this.isEditing = false;
  }

  moveSelection(amount: number, event?: Event): void {
    event?.preventDefault();
    if (this.isSelectionMoved) return;
    this.isSelectionMoved = true;
    setTimeout(() => (this.isSelectionMoved = false), 2500);

    let rows = this.body.nativeElement.childNodes;
    // rows.forEach((row: HTMLElement, index: number) => {
    for (let index = 0; index < rows.length; index++) {
      let row = rows[index];
      if (row.classList && row.classList.contains('selected')) {
        let newIndex = index + amount;
        if (newIndex >= 0 && newIndex < rows.length - 1) {
          this.selectRow(rows[newIndex], this.source[newIndex]);
        }
        break;
      }
    }
  }

  focus(): void {
    !this.isEditing && this.tableEventShifter.nativeElement.focus();
  }

  enableMove(): void {
    this.isSelectionMoved = false;
  }
}
