import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient, private snackBar: MatSnackBar, private router: Router) {
    this.http.get('/api/user/info', { headers: { 'X-Requested-With': 'XMLHttpRequest' }}).subscribe(info => {
      this.snackBar.open('Willkommen zurück');
      this.info = info;
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
      this.router.navigateByUrl('/user');
    }, error => {
      this.snackBar.open('Anmeldung fehlgeschlagen');
      this.info = undefined;
    });
  }

  getOptions() {
    return {
      headers: {
        Authorization: `Basic ${btoa(`${this.email}:${this.password}`)}`,
        'X-Requested-With': 'XMLHttpRequest'
      }
    };
  }
}
