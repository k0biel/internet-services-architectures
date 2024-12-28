import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MaterialType } from '../models/material.type';

@Injectable({
  providedIn: 'root'
})
export class MaterialService {
  private apiUrl = 'http://localhost:8085/materials';

  constructor(private http: HttpClient) {}

  getAllMaterials(): Observable<MaterialType[]> {
    return this.http.get<MaterialType[]>(this.apiUrl);
  }

  getMaterialById(id: string): Observable<MaterialType> {
    return this.http.get<MaterialType>(`${this.apiUrl}/${id}`);
  }

  createMaterial(material: MaterialType): Observable<MaterialType> {
    return this.http.post<MaterialType>(`${this.apiUrl}/${material.warehouseId}`, material);
  }

  updateMaterial(id: string, material: MaterialType): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/${id}`, material);
  }

  deleteMaterial(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }

  getMaterialsByWarehouseId(warehouseId: string): Observable<MaterialType[]> {
    return this.http.get<MaterialType[]>(`${this.apiUrl}/warehouse/${warehouseId}`);
  }
}
