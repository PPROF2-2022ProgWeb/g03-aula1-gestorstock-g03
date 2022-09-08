import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RegistroService } from 'src/app/servicios/registro.service';
import { Iconos } from 'src/app/utils/iconos.enum';

@Component({
  selector: 'app-registro',
  templateUrl: './registro.component.html',
  styleUrls: ['./registro.component.css']
})
export class RegistroComponent implements OnInit {

  nombre = new FormControl('', []);
  apellido = new FormControl('', []);
  pais = new FormControl('');
  provincia = new FormControl('', []);
  fechaNacimiento = new FormControl('');
  confirmarContraseña = new FormControl('', [], []);
  empresa = new FormControl('', []);
  descripcion = new FormControl('', []);
  mail = new FormControl('', [], []);
  password = new FormControl('', [], []);
  
  form: any;


  mostrar = false;
  public iconos = Iconos;

  constructor(private formBuilder: FormBuilder,
    private registroService: RegistroService,
    private router: Router) {
    this.form = this.formBuilder.group({
      nombre: ['', [Validators.minLength(4)]],
      apellido: ['', [Validators.minLength(4)]],
      pais: [Validators.required],
      provincia: ['', [Validators.minLength(4)]],
      fechaNacimiento: [Validators.required],
      confirmarContraseña: ['', [Validators.required, Validators.minLength(6)]],
      empresa: ['', [Validators.minLength(4)]],
      descripcion: ['', [Validators.minLength(10)]],
      password: ['', [Validators.required, Validators.minLength(6)]],
      mail: ['', [Validators.required, Validators.email]]
    });
  }

  ngOnInit(): void {
  }


  cargarEmpresa() {
    this.mostrar = true;
  }

  
  get mailValid(){
    return this.form.get('mail')?.touched && !this.form.get('mail')?.valid;
  }

  get mailErrorMessage(){
    if(this.mail.hasError('required')){
      return "El correo es requerido"
    }else {
      return "Formato de correo no valido"
    }
  }

  get passwordValid(){
    return this.form.get('password')?.touched && !this.form.get('password')?.valid;
  }

  get passwordErrorMessage(){
    return "Debe contener mínimo 6 caracteres"
  }

  get nombreValid(){
    return !this.form.get('nombre')?.valid;
  }
  get nombreErrorMessage(){
    return "Debe contener mínimo 4 caracteres"
  }

  get apellidoValid(){
    return !this.form.get('apellido')?.valid;
  }
  get apellidoErrorMessage(){
    return "Debe contener mínimo 4 caracteres"
  }

  

  

  registro(){
    this.registroService.registro(this.mail, this.password)
    .then(() => {
      this.router.navigate(['login']);
    })
    .catch(() => {
      alert("Revise los datos ingresados.")
    });    
  }


}
