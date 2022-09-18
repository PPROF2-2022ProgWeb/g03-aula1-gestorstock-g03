import { Injectable } from "@angular/core";
import { FormControl } from "@angular/forms";

@Injectable()
export class LoginService{
    
    
    constructor(){}

  login(mail: FormControl, password: FormControl): Promise<any>{
    return new Promise((resolve, reject) => {
      this.login(mail, password)
      .then((datos) => resolve(datos),
          err => reject(err))        
    });
  }
}
