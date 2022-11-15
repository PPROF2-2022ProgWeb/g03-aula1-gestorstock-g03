import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

export function checkUnique(exist: boolean):  ValidatorFn { 
  return (control: AbstractControl): ValidationErrors | null => {
    if(exist) console.log('existe');
    return exist ? {
      notUnique: true,
    } : null
  }
}