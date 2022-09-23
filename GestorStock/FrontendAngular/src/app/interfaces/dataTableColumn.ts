import {PipeTransform} from "@angular/core"
export interface dataTableColumn{
  name:string;
  source:string;
  pipe?:any;
  pipeArgs?:[]; 
}