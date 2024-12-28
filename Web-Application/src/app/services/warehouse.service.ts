import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { WarehouseType } from '../models/warehouse.type';

@Injectable({
  providedIn: 'root'
})
export class WarehouseService {
  private apiUrl = 'http://localhost:8085/warehouses';

  constructor(private http: HttpClient) {}

  getAllWarehouses(): Observable<WarehouseType[]> {
    return this.http.get<WarehouseType[]>(this.apiUrl);
  }

  getWarehouseById(id: string): Observable<WarehouseType> {
    return this.http.get<WarehouseType>(`${this.apiUrl}/${id}`);
  }

  createWarehouse(warehouse: WarehouseType): Observable<WarehouseType> {
    return this.http.post<WarehouseType>(this.apiUrl, warehouse);
  }

  updateWarehouse(id: string, warehouse: WarehouseType): Observable<void> {
    return this.http.put<void>(`${this.apiUrl}/${id}`, warehouse);
  }

  deleteWarehouse(id: string): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
