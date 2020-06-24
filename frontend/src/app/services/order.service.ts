import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class OrderService {

  constructor(private http: HttpClient, private snack: MatSnackBar) { }

  getMyOpenOrders(): Observable<any[]> {
    return this.http.get<any[]>('/api/order/open', { headers: { 'X-Requested-With': 'XMLHttpRequest' }});
  }

  getMysentOrders(): Observable<any[]> {
    return this.http.get<any[]>('/api/order/sent', { headers: { 'X-Requested-With': 'XMLHttpRequest' }});
  }

  getOrderDetails(orderNumber: number): any {
    return this.http.get(`/api/order/details/${orderNumber}`);
  }

  getAllOpenOrders(): Observable<any[]> {
    return this.http.get<any[]>('/api/order/admin/open', { headers: { 'X-Requested-With': 'XMLHttpRequest' }});
  }

  setOrderStatusSent(orderNumber: number) {
    this.http.get(`/api/order/admin/sent/${orderNumber}`).subscribe(
      () => this.snack.open('Bestellstatus wurde auf VERSCHICKT geändert', undefined, { duration: 5000 }),
      error => this.snack.open(error.error.message, undefined, { duration: 5000 })
    );
  }
}
