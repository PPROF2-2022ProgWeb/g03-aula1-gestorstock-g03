import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Producto } from '../models/Producto';

@Injectable({
  providedIn: 'root'
})
export class ProductosService {

  private backendUrl = environment.backendUrl;

  private httpOptions = {
    header: new HttpHeaders({
      'Content-Type': 'application/json'
    })
  }

  constructor(private http: HttpClient) { }

  getAllProducts(): Observable<Producto[]> {
    return this.http.get<Producto[]>(`${this.backendUrl}/producto`)
  }
}
