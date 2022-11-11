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

  public lista(): Observable<ProductoModel[]>{
    return this.http.get<ProductoModel[]>(`${this.backendUrl}producto`);
  }

  public listaNombreProducto(nombreProducto: string): Observable<ProductoModel[]> {
    return this.http.get<ProductoModel[]>(`${this.backendUrl}producto/${nombreProducto}`);
  }

  public buscarProducto(id: number): Observable<ProductoModel>{
    return this.http.get<ProductoModel>(`${this.backendUrl}producto/${id}`);
  }

  public agregar(productoModel: ProductoModel): Observable<any>{
    return this.http.post<any>(`${this.backendUrl}create`,productoModel);
  }

  public modificar(id: number, productoModel: ProductoModel): Observable<any>{
    return this.http.put<any>(`${this.backendUrl}update/${id}`,productoModel);
  }

  public eliminar(id: number): Observable<any>{
    return this.http.delete<any>(`${this.backendUrl}delete/${id}`);
  }

  getAllProducts(): Observable<ProductoModel[]> {
    return this.http.get<ProductoModel[]>(`${this.backendUrl}/producto`)
  }
}
