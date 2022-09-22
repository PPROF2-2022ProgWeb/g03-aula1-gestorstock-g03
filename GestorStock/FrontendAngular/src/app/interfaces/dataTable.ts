import {PipeTransform} from "@angular/core"
export interface IDataTableColumn{
  name:string;
  source:string;
  pipe?:any;
  pipeArgs?:[]; 
}

export interface IDataTableSelectedRow {
  element: HTMLElement;
  item: any;
}

export interface IDataTableSelectionChanged {
  previous: IDataTableSelectedRow;
  current: IDataTableSelectedRow;
}