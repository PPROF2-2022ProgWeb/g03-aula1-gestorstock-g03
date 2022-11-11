import { Injectable } from '@angular/core';
import { environment } from 'src/environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ProductoModel } from '../models/ProductoModel';
import { TipoProducto } from '../models/TipoProducto';

@Injectable({
  providedIn: 'root',
})
export class ProductosService {
  private backendUrl = environment.backendUrl;
  private url = `${this.backendUrl}/producto`;
  private httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
    }),
  };

  constructor(private http: HttpClient) {}

  public cargarProductos(): Observable<ProductoModel[]> {
    return this.http.get<ProductoModel[]>(`${this.url}`);
  }

  public cargarCategorias(): Observable<TipoProducto[]> {
    return this.http.get<TipoProducto[]>(`${this.backendUrl}/tipoProducto`);
  }

  public cargarPorNombreProducto(
    nombreProducto: string
  ): Observable<ProductoModel[]> {
    return this.http.get<ProductoModel[]>(
      `${this.url}/${nombreProducto}`
    );
  }

  public buscarProducto(id: number): Observable<ProductoModel> {
    return this.http.get<ProductoModel>(`${this.url}/${id}`);
  }

  public agregar(productoModel: ProductoModel): Observable<ProductoModel> {
    return this.http.post<ProductoModel>(
      `${this.url}create`,
      productoModel,
      this.httpOptions
    );
  }

  public modificar(productoModel: ProductoModel): Observable<ProductoModel> {
    return this.http.put<ProductoModel>(
      `${this.url}/${productoModel.idProducto}`,
      productoModel,
      this.httpOptions
    );
  }

  public eliminar(id: number): Observable<ProductoModel> {
    return this.http.delete<ProductoModel>(`${this.url}/${id}`);
  }

}
