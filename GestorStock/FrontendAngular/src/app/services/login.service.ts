import { Injectable } from "@angular/core";
import { FormControl } from "@angular/forms";

@Injectable()
export class LoginService{
  getAuth: any;
    
    
  constructor(){}

  login(mail: FormControl, password: FormControl): Promise<any>{
    return new Promise((resolve, reject) => {
      resolve(true),
      reject(false)        
    });
  }

  // getAuth(){
  //   return this.authService.authState.pipe(
  //       map( auth => auth) // con este metodo nos va a regresar el uss que se ha autenticado a la bbdd
  //   );
  // }

}
