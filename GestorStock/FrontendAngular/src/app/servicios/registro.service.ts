import { Injectable } from "@angular/core";
import { FormControl } from "@angular/forms";

Injectable()
export class RegistroService{

    constructor(){}

    registro(mail: FormControl, password: FormControl): Promise<any>{
        return new Promise((resolve, reject) => {
            this.registro(mail, password)
            .then((datos) => resolve(datos),
                err => reject(err))        
          });
    }


}