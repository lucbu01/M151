import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private http: HttpClient, private snack: MatSnackBar, private router: Router) { }

  getProductList(): Observable<any[]> {
    return this.http.get<any[]>('/api/product/list');
  }

  getProductByNumber(productNumber: number): Observable<any> {
    return this.http.get<any>(`/api/product/get/${productNumber}`);
  }

  createProduct(product: any): Observable<number> {
    return this.http.post<any>(`/api/product/create`, product, { headers: { 'X-Requested-With': 'XMLHttpRequest' }});
  }

  updateProduct(productNumber: number, product: any) {
    this.http.patch(`/api/product/update/${productNumber}`, product, { headers: { 'X-Requested-With': 'XMLHttpRequest' }})
      .subscribe(
        () => this.snack.open('Der Artikel wurde erfolgreich geändert', undefined, { duration: 5000 }),
        error => this.snack.open(error.error.message, undefined, { duration: 5000 })
      );
  }

  deleteProduct(productNumber: number) {
    this.http.delete(`/api/product/delete/${productNumber}`, { headers: { 'X-Requested-With': 'XMLHttpRequest' }})
      .subscribe(
        () => {
          this.snack.open('Der Artikel wurde erfolgreich gelöscht', undefined, { duration: 5000 });
          this.router.navigateByUrl('/');
        },
        error => this.snack.open(error.error.message, undefined, { duration: 5000 })
      );
  }
}
