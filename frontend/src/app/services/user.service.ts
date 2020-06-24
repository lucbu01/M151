import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient, private snack: MatSnackBar, private router: Router) {
    this.http.get('/api/user/info', { headers: { 'X-Requested-With': 'XMLHttpRequest' }}).subscribe(info => {
      this.info = info;
      this.snack.open(`Willkommen zurück, ${this.info.firstName}!`, undefined, { duration: 5000 });
    }, error => this.info = undefined);
  }

  private info: any;
  private email: string;
  private password: string;

  isLoggedIn(): boolean {
    return this.info != null;
  }

  isAdmin(): boolean {
    return this.isLoggedIn() && this.info.role === 'ADMIN';
  }

  login(email: string, password: string) {
    this.email = email;
    this.password = password;
    this.http.get('/api/user/info', this.getOptions()).subscribe(info => {
      this.info = info;
      this.snack.open(`Willkommen, ${this.info.firstName}!`, undefined, { duration: 5000 });
      this.router.navigateByUrl('/user');
    }, error => {
      this.snack.open('Anmeldung fehlgeschlagen', undefined, { duration: 5000 });
      this.info = undefined;
    });
  }

  register(postValue: any) {
    this.http.post('/api/user/create', postValue).subscribe(
      successful => this.login(postValue.email, postValue.password),
      error => this.snack.open(error.error.message, undefined, { duration: 5000 }));
  }

  getInfo(): Observable<any> {
    return this.http.get('/api/user/info', { headers: { 'X-Requested-With': 'XMLHttpRequest' }})
      .pipe(tap(info => this.info = info, () => this.info = undefined));
  }

  getOptions() {
    return {
      headers: {
        Authorization: `Basic ${btoa(`${this.email}:${this.password}`)}`,
        'X-Requested-With': 'XMLHttpRequest'
      }
    };
  }

  logout() {
    this.info = undefined;
    this.email = undefined;
    this.password = undefined;
    this.http.get('/api/user/logout', { headers: { 'X-Requested-With': 'XMLHttpRequest' }}).subscribe();
    this.snack.open('Auf Wiedersehen');
    this.router.navigateByUrl('/');
  }
}
