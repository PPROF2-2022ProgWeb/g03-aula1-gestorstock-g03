import {PipeTransform} from "@angular/core"
export interface IDataTableColumn{
  name:string;
  source:string;
  pipe?:any;
  pipeArgs?:[];
  editable?: boolean;
  type?: 'number' | 'string';
}

export interface IDataTableSelectedRow {
  element: HTMLElement;
  item: any;
}

export interface IDataTableSelectionChanged {
  previous?: IDataTableSelectedRow;
  current?: IDataTableSelectedRow;
}

export interface IDataTableEditing {
  item: any;
  prop: string;
  prevValue: any;
  newValue: any;
}
export interface IDataTableEditionFinished {
  state: 'accepted' | 'rejected';
  item: any;
  prop: string;
  prevValue: any;
  newValue: any;
}