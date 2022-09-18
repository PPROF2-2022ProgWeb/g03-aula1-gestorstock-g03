import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'app-icon',
  template: `<i [class]="['gs-icon', icon]"><ng-content></ng-content></i>`,
})
export class IconComponent {
  @Input() icon: string
}
