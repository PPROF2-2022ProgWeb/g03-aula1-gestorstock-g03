import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  
  mail = new FormControl ('',[],[]);
  password = new FormControl ('',[],[]);
  form: any;

  constructor(private formBuilder: FormBuilder) { 
    this.form = this.formBuilder.group({
      password:['',[Validators.required, Validators.minLength(6)]],
      mail:['',[Validators.required, Validators.email]]
    });
  }

  ngOnInit(): void {
  }
  
  get Password(){
    return this.form.get('password');    
  }
  get Mail(){
    return this.form.get('mail');
  }

  get PasswordValid(){
    return this.Password?.touched && !this.Password?.valid;
  }
  
  get MailValid(){
    return this.Mail?.touched && !this.Mail?.valid;
  }

  onEnviar(event: Event){
    event.preventDefault;
    if(this.form.valid){
      alert("Enviar al servidor");      
    } else{
      this.form.markAllAsTouched();
    }
  }
 

}
