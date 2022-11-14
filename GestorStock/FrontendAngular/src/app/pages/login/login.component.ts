import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Iconos } from 'src/app/utils/iconos.enum';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  mail = new FormControl('', [], []);
  password = new FormControl('', [], []);
  form: any;

  public iconos = Iconos;

  constructor(
    private formBuilder: FormBuilder,
    private router: Router
  ) {
    this.form = this.formBuilder.group({
      password: ['', [Validators.required, Validators.minLength(6)]],
      mail: ['', [Validators.required, Validators.email]],
    });
  }

  ngOnInit(): void {}

  get passwordValid() {
    if (this.form.get('password')?.touched) {
      return this.form.get('password')?.valid;
    }
    return true;
  }

  get mailValid() {
    if (this.form.get('mail')?.touched) {
      return this.form.get('mail')?.valid;
    }
    return true;
  }

  get formValid() {
    if (this.form.get('mail')?.touched && this.form.get('password')?.touched) {
      return this.mailValid && this.passwordValid;
    } else {
      return false;
    }
  }

  get passwordErrorMessage() {
    return 'Debe ser de 6 o más caracteres';
  }

  get mailErrorMessage() {
    if (this.mail.hasError('required')) {
      return 'El correo es requerido';
    } else {
      return 'Formato de correo no valido';
    }
  }

  login() {
    this.form.get('mail').touched = true;
    this.form.get('password').touched = true;

    if (this.formValid) {
      this.router.navigate(['dashboard']);
    }
  }
}
