import { Directive, ElementRef, HostListener, Input } from '@angular/core';

@Directive({
  selector: 'img[fallbackSrc]'
})
export class ImageFallbackDirective {
  @Input() fallbackSrc: string;

  constructor(private eRef: ElementRef) { }

  @HostListener('error')
  loadFallbackOnError() { 
    const element: HTMLImageElement = <HTMLImageElement> this.eRef.nativeElement;
    element.src = this.fallbackSrc || '../../../../assets/img/no-image.jpg'
  }
}
