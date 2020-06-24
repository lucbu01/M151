import { Component, OnInit } from '@angular/core';
import { OrderService } from 'src/app/services/order.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.scss']
})
export class AdminComponent implements OnInit {

  constructor(private orderService: OrderService, private router: Router, private snack: MatSnackBar) { }

  openOrders: any[];
  displayedColumns = [ 'orderNumber', 'timestamp', 'totalPrice' ];
  searchForm = new FormGroup({
    orderNumber: new FormControl('')
  });

  ngOnInit(): void {
    this.orderService.getAllOpenOrders().subscribe(openOrders => this.openOrders = openOrders, error => {
      this.snack.open(error.error.message, undefined, { duration: 5000 });
      this.router.navigateByUrl('/');
    });
  }

  search() {
    if (this.searchForm.valid) {
      let orderNumber;
      try {
        orderNumber = parseInt(this.searchForm.value.orderNumber.trim(), 10);
      } catch {
        this.snack.open('Die Bestellung wurde nicht gefunden', undefined, { duration: 5000 });
      }
      if (orderNumber) {
        this.orderService.getOrderDetails(this.searchForm.value.orderNumber.trim()).subscribe(
          order => this.router.navigateByUrl(`order/${order.number}`),
          () => this.snack.open('Die Bestellung wurde nicht gefunden', undefined, { duration: 5000 })
        );
      }
    }
  }
}
