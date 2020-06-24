import { Injectable, EventEmitter } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  constructor(private http: HttpClient, private snack: MatSnackBar) { }

  onChange = new EventEmitter();

  add(productNumber: number) {
    this.http.put(`/api/cart/add/${productNumber}`,  { headers: { 'X-Requested-With': 'XMLHttpRequest' }}).subscribe(
      () => {
        this.snack.open('Das Produkt wurde dem Warenkorb hinzugefügt', undefined, { duration: 5000 });
        this.detectChanged();
      },
      () => this.notLoggedIn());
  }

  remove(productNumber: number) {
    this.http.delete(`/api/cart/remove/${productNumber}`,  { headers: { 'X-Requested-With': 'XMLHttpRequest' }}).subscribe(
      () => {
        this.snack.open('Ein Produkt wurde aus dem Warenkorb entfernt', undefined, { duration: 5000 });
        this.detectChanged();
      },
      () => this.notLoggedIn());
  }

  removePosition(productNumber: number) {
    this.http.delete(`/api/cart/remove/${productNumber}/all`,  { headers: { 'X-Requested-With': 'XMLHttpRequest' }}).subscribe(
      () => {
        this.snack.open('Eine Position wurde aus dem Warenkorb entfernt', undefined, { duration: 5000 });
        this.detectChanged();
      },
      () => this.notLoggedIn());
  }

  set(productNumber: number, count: number) {
    this.http.put(`/api/cart/set/${productNumber}/${count}`,  { headers: { 'X-Requested-With': 'XMLHttpRequest' }}).subscribe(
      () => {
        this.snack.open('Die Anzahl einer Position wurde geändert', undefined, { duration: 5000 });
        this.detectChanged();
      },
      () => this.notLoggedIn());
  }

  get(): Observable<any> {
    return this.http.get('/api/cart/get',  { headers: { 'X-Requested-With': 'XMLHttpRequest' }});
  }

  detectChanged() {
    if (this.onChange.observers.length > 0) {
      this.get().subscribe(cart => this.onChange.emit(cart));
    }
  }

  notLoggedIn() {
    this.snack.open('Sie sind nicht angemeldet!', undefined, { duration: 5000 });
  }
}
