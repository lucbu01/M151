import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient) { }

  getProductList(): Observable<any[]> {
    return this.http.get<any[]>('/api/product/list');
  }

  getProductByNumber(productNumber: number): Observable<any> {
    return this.http.get<any>(`/api/product/get/${productNumber}`);
  }
}
