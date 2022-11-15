import { AfterViewChecked, Component, OnInit, ViewChild } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { TextInputComponent } from 'src/app/components/form-controls/text-input/text-input.component';
import { Provincia } from 'src/app/models/LocationModels';
import { Usuario } from 'src/app/models/Usuario.model';
import { LocationService } from 'src/app/services/location.service';
import { UsuarioService } from 'src/app/services/usuario.service';
import { Iconos } from 'src/app/utils/iconos.enum';
import { Roles } from 'src/app/utils/roles.enum';
import { checkUnique } from 'src/app/utils/validators/notUnique.validator';
import { checkPasswords } from 'src/app/utils/validators/password.validator';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css'],
})
export class RegistroComponent implements OnInit {
  // @ViewChild('modalError') modalError: ModalComponent;
  public isVendedor: boolean = false;
  public Steps = StepsRegistro;
  public currentStep: StepsRegistro = 0;
  public provincias: Provincia[] = [];
  public usernameExist = false;
  public formUsuario = new FormGroup(
    {
      usuario: new FormControl('', [
        Validators.required,
        Validators.minLength(4),
        checkUnique(this.usernameExist),
      ]),
      contraseña: new FormControl('', [
        Validators.required,
        Validators.minLength(4),
      ]),
      confirmarContraseña: new FormControl('', [
        Validators.required,
        Validators.minLength(4),
      ]),
      correo: new FormControl('', [Validators.required, Validators.email]),
      vendedor: new FormControl(false),
    },
    { validators: [checkPasswords] }
  );

  public formDatosPersonales = new FormGroup({
    nombre: new FormControl('', [Validators.required, Validators.minLength(4)]),
    apellido: new FormControl('', [
      Validators.required,
      Validators.minLength(4),
    ]),
    provincia: new FormControl(null, [Validators.required]),
    dni: new FormControl(null, [Validators.required]),
    celular: new FormControl('', [Validators.required]),
    direccion: new FormControl('', [Validators.required]),
  });

  public iconos = Iconos;

  constructor(
    private us: UsuarioService,
    private router: Router,
    private ls: LocationService
  ) {}

  ngOnInit(): void {
    this.ls.getProvincias().subscribe((data) => (this.provincias = data));
  }

  public onCheckVendedorChange(e: Event): void {
    let target = e.target as HTMLInputElement;
    this.isVendedor = target.checked;
  }

  onFieldUsuarioChange(): void {
    this.usernameExist = false;
  }

  public fieldValid(form: FormGroup, fieldName: string): boolean {
    let field = form.get(fieldName);
    if (fieldName === 'confirmarContraseña') {
      return !form.hasError('notSame');
    }
    return field && field.touched ? field.valid : true;
  }

  getErrorMessage(form: FormGroup, fieldName: string) {
    let field = form.get(fieldName);
    if (!field || !field.errors) return '';
    if (fieldName === 'confirmarContraseña') {
      return 'Las contraseñas no coinciden';
    }

    if (field.hasError('minlength')) {
      let min = field.errors['minlength'].requiredLength;
      return `El campo ${fieldName} debe tener ${min} caracteres`;
    }
    if (field.hasError('email')) {
      return `El campo correo no tiene un formato valido`;
    }
    if (field.hasError('notUnique')) {
      return `El nombre de usuario ya existe`;
    }
    if (field.hasError('required')) {
      return `El campo ${fieldName} es requerido`;
    }
    return '';
  }

  public onNextClick() {
    this.formUsuario.markAllAsTouched();
    this.formUsuario.updateValueAndValidity();
    if (this.formUsuario.valid) this.currentStep++;
  }

  public onFinalizarClick() {
    this.formUsuario.markAllAsTouched();
    this.formUsuario.updateValueAndValidity();
    if (!this.formUsuario.valid) return;

    if (this.currentStep === StepsRegistro.DATOS_PERSONALES) {
      this.formDatosPersonales.markAllAsTouched();
      this.formDatosPersonales.updateValueAndValidity();
      if (!this.formDatosPersonales.valid) return;
    }
    const { usuario, contraseña, correo } = this.formUsuario.value;
    const { nombre, apellido, provincia, dni, celular, direccion } =
      this.formDatosPersonales.value;

    let newUser = new Usuario();
    newUser.username = usuario;
    newUser.password = contraseña;
    newUser.correo = correo;
    newUser.apellido = apellido;
    newUser.nombre = nombre;
    newUser.idProvincia = provincia;
    newUser.cedula = dni;
    newUser.celular = celular;
    newUser.direccion = direccion;
    newUser.roles = null;

    let roles = [Roles.ROLE_USER];
    this.isVendedor && roles.push(Roles.ROLE_SELLER);
    console.log(newUser);
    this.us.registrarUsuario(newUser, roles).subscribe({
      next: (data) => {
        console.log(data);
        this.router.navigate(['/login']);
      },
      error: (error) => {
        console.log(error);
        this.currentStep = 0;
        this.usernameExist = true;
      },
    });
  }

  public onPrevClick() {
    if (this.currentStep !== StepsRegistro.CREACION_USUARIO) {
      this.currentStep--;
    }
    console.log(this.currentStep);
  }
}

export enum StepsRegistro {
  CREACION_USUARIO = 0,
  DATOS_PERSONALES = 1,
}
