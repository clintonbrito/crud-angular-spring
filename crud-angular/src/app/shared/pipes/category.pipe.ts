import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
  name: 'category'
})
export class CategoryPipe implements PipeTransform {

  transform(value: string, ...args: unknown[]): string {
    switch(value) {
      case 'front-end': return 'brush';
      case 'back-end': return 'code';
    }
    return 'computer';
  }

}
