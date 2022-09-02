import { NgModule } from '@angular/core';
import { LogoSvgComponent } from './logo.component';



@NgModule({
  declarations: [
    LogoSvgComponent
  ],
  exports: [
    LogoSvgComponent,
  ]
})
export class SvgModule { }
