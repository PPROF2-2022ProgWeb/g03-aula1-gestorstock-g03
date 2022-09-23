import { Injector, Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'dynamicPipe',
})
export class DynamicPipe implements PipeTransform {
  public constructor(private injector: Injector) {}

  transform(value: any, pipeToken: any, pipeArgs?: any[]): any {
    if (!pipeToken) {
      return value;
    } else {
      if(!pipeArgs) pipeArgs = []
      let pipe = this.injector.get(pipeToken);
      return pipe.transform(value, ...pipeArgs);
    }
  }
}
