import { Component, OnInit } from '@angular/core';
import {
  FormBuilder,
  FormControl,
  FormGroup,
  Validators,
} from '@angular/forms';
import { Router } from '@angular/router';
import { LoginUser } from 'src/app/models/Usuario.model';
import { UsuarioService } from 'src/app/services/usuario.service';
import { Iconos } from 'src/app/utils/iconos.enum';
import { Roles } from 'src/app/utils/roles.enum';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
})
export class LoginComponent implements OnInit {
  public form: FormGroup = new FormGroup({
    username: new FormControl('', [
      Validators.required,
      Validators.minLength(4),
    ]),
    password: new FormControl('', [
      Validators.required,
      Validators.minLength(4),
    ]),
  });

  public iconos = Iconos;

  constructor(private router: Router, private us: UsuarioService) {}

  ngOnInit(): void {}

  public fieldValid(form: FormGroup, fieldName: string): boolean {
    let field = form.get(fieldName);
    return field && field.touched ? field.valid : true;
  }

  getErrorMessage(form: FormGroup, fieldName: string) {
    let field = form.get(fieldName);
    if (!field || !field.errors) return '';

    if (field.hasError('minlength')) {
      let min = field.errors['minlength'].requiredLength;
      return `El campo ${fieldName} debe tener ${min} caracteres`;
    }
    if (field.hasError('required')) {
      return `El campo ${fieldName} es requerido`;
    }
    return '';
  }

  onLoginClick() {
    const { username, password } = this.form.value;
    console.log('logueando');
    this.us.login(username, password).subscribe({
      next: (data: LoginUser) => {
        sessionStorage.setItem('loggedInUser', JSON.stringify(data));
        for (const authority of data.authorities) {
          if (authority.authority === Roles.ROLE_SELLER) {
            this.router.navigate(['/dashboard']);
            return;
          }
        }
        this.router.navigate(['/tienda']);
      },
      error: (err) => console.log(err),
    });
  }
}
