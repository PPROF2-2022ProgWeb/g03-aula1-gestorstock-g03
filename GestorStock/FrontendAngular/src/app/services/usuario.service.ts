import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable, switchMap } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Rol } from '../models/Rol.model';
import { Usuario } from '../models/Usuario.model';
import { Roles } from '../utils/roles.enum';

@Injectable({
  providedIn: 'root',
})
export class UsuarioService {
  private regUrl = `${environment.backendUrl}/usuario`;
  private rolUrl = `${environment.backendUrl}/rol`;
  private loginUrl = `${environment.backendUrl}/auth/login`;
  constructor(private http: HttpClient) {}

  public registrarUsuario(usuario: Usuario, roles: Roles[]) {
    return this.http.post<Usuario>(this.regUrl, usuario).pipe(
      map((usuario) => {
        roles.forEach((rol) => {
          const rolObj = new Rol();
          rolObj.idRol = null;
          rolObj.idUsuario = usuario.idUsuario;
          rolObj.nombreRol = rol;
          this.http.post(this.rolUrl, rolObj)
        })
        return usuario;
      })
    );
  }
}
