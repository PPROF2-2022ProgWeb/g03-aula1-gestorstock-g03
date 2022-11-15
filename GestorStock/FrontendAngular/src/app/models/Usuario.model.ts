export class Usuario {
  public idUsuario: number | null;
  public username: string;
  public password: string;
  public cedula: number;
  public nombre: string;
  public apellido: string;
  public celular: string;
  public direccion: string;
  public tel: string;
  public roles: any[] | null;
  public idProvincia: number;
  public correo: string;
}

export class LoginUser {
  token: string;
  bearer: string;
  nombreUsuario: string;
  authorities: {authority: string}[]
}