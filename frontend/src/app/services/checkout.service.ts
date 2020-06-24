import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CheckoutService {

  constructor(private httpClient: HttpClient) { }

  checkoutPreview(): Observable<any> {
    return this.httpClient.get('/api/checkout/preview');
  }

  cancelCheckoutPreview() {
    this.httpClient.delete('/api/checkout/cancel').subscribe();
  }

  orderCheckoutPreview(): Observable<any> {
    return this.httpClient.post('/api/checkout/order', {}, { headers: { 'X-Requested-With': 'XMLHttpRequest' }});
  }
}
