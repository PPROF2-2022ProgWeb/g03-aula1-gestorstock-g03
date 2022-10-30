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
  @Input() caseSensitive: boolean = false;
  @Input() autocomplete: boolean = false;
  @Input() searchResultMode: 'firstMatch' | 'all' = 'all';
  @Input() searchMode: 'startsWith' | 'include' | 'exact' = 'include';

  @Output() searchDone = new EventEmitter<SearchResult>();
  @Output() reset = this.resetSearch

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
    let res;
    let value = this.input.nativeElement.value as string;
    if(!this.caseSensitive){
      value = value.toLowerCase();
    }
    if(this.searchResultMode === 'firstMatch'){
      console.log('entro en first');
      return this.searchFirstMatch(value, this.caseSensitive)
      console.log('dentro de first despues del return');
    }
    console.log('despues del if');
    
    switch (this.searchMode) {
      case 'startsWith':
        res = this.searchStartsWith(value, this.caseSensitive);
        break;

      case 'include':
        res = this.searchInclude(value, this.caseSensitive)
        break;

      case 'exact':
        res = this.searchExact(value, this.caseSensitive);
        break;
      
    }
    return res || null;
  }
  
  searchFirstMatch(value: string, caseSensitive: boolean = false): any | null {
    return this.source.find(x => {
      let element = x[this.searchProperty]
      if (typeof element !== 'string') {
        return false;
      }
      if(!caseSensitive) element = element.toLowerCase();

      switch (this.searchMode) {
        case 'startsWith':
          return element.startsWith(value)
  
        case 'include':
          return element.includes(value)
  
        case 'exact':
          return element === value;
      }
    })
  }
  

  searchInclude(value: string, caseSensitive: boolean = false): any[] {
    return this.source.filter((x) => {
      let element = x[this.searchProperty]
      if (typeof element !== 'string') {
        return false;
      }
      if(!caseSensitive) element = element.toLowerCase()
      return element.includes(value);
    });
  }
  
  searchStartsWith(value: string, caseSensitive: boolean = false): any[] {
    return this.source.filter((x) => {
      let element = x[this.searchProperty]
      if (typeof element !== 'string') {
        return false;
      }
      if(!caseSensitive) element = element.toLowerCase()
      return element.startsWith(value);
    });
  }

  searchExact(value: string, caseSensitive: boolean = false): any[] {
    return this.source.filter((x) => {
      let element = x[this.searchProperty]
      if (typeof element !== 'string') {
        return false;
      }
      if(!caseSensitive) element = element.toLowerCase();
      return element === value;
    });
  }

  onSearchDone(): void {
    let res = {
      source: this.source,
      searchProperty: this.searchProperty,
      result: this.search(),
      value: this.input.nativeElement.value
    };
    this.searchDone.emit(res);
  }

  resetSearch(): void {
    this.input.nativeElement.value = '';
  }
}
