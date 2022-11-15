import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

export const checkPasswords: ValidatorFn = (group: AbstractControl):  ValidationErrors | null => { 
  let pass = group.get('contraseña')?.value;
  let confirmPass = group.get('confirmarContraseña')?.value

  return pass && confirmPass && pass !== confirmPass ? { notSame: true } : null
}