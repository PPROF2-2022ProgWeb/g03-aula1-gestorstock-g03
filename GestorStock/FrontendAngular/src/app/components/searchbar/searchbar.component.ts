import {
  AfterViewInit,
  Component,
  ElementRef,
  EventEmitter,
  Input,
  OnInit,
  Output,
  ViewChild,
} from '@angular/core';
import { SearchResult } from 'src/app/interfaces/searchResult';

@Component({
  selector: 'search-bar',
  templateUrl: './searchbar.component.html',
  styleUrls: ['./searchbar.component.css'],
})
export class SearchbarComponent implements OnInit, AfterViewInit {
  @Input() name: string;
  @Input() id: string;
  @Input() placeholder: string = 'Titulo';
  @Input() source: any[];
  @Input() searchProperty: string;
  @Input() triggerOnInput: string;
  @Input() caseSensitive: boolean = true;

  @Output() searchDone = new EventEmitter<SearchResult>();

  @ViewChild('input') input: ElementRef;

  constructor() {}

  ngOnInit(): void {}

  ngAfterViewInit(): void {
    if (this.triggerOnInput !== undefined) {
      this.input.nativeElement.addEventListener('input', () => {
        this.onSearchDone();
      });
    }
  }

  search(): any[] | null {
    let value = this.input.nativeElement.value;
    return this.source.filter((x) => {
      let element = x[this.searchProperty];
      let type = typeof element;
      if (type !== 'string') {
        return false;
      }
      if (this.caseSensitive) element = element.toLowerCase();
      return element.includes(value.toLowerCase());
    });
  }

  onSearchDone(): void {
    let res = {
      source: this.source,
      searchProperty: this.searchProperty,
      result: this.search(),
    };
    this.searchDone.emit(res);
  }
}
