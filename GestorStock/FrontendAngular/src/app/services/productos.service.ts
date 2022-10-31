import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ProductoModel } from '../models/ProductoModel';

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

  getAllProducts(): Observable<ProductoModel[]> {
    return this.http.get<ProductoModel[]>(`${this.backendUrl}/producto`)
  }
}
