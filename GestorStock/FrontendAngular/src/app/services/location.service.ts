import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Departamento, Provincia } from '../models/LocationModels';

@Injectable({
  providedIn: 'root'
})
export class LocationService {
  constructor(private http: HttpClient) { }

  public getProvincias(): Observable<Provincia[]> {
    return this.http.get<Provincia[]>(`${environment.backendUrl}/provincia`);
  }

  public getDepartamentos(): Observable<Departamento[]> {
    return this.http.get<Departamento[]>(`${environment.backendUrl}/departamento`);
  }
}