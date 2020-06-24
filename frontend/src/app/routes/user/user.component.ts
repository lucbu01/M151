import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { OrderService } from 'src/app/services/order.service';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

  user: any;
  openOrders: any[];
  sentOrders: any[];
  displayedColumns = [ 'orderNumber', 'timestamp', 'totalPrice' ];

  constructor(public userService: UserService, private orderService: OrderService, private snack: MatSnackBar, private router: Router) { }

  ngOnInit(): void {
    this.userService.getInfo().subscribe(info => {
      this.user = info;
      this.orderService.getMyOpenOrders().subscribe(
        openOrders => this.openOrders = openOrders,
        error => this.snack.open(error.error.message, undefined, { duration: 5000 })
      );
      this.orderService.getMysentOrders().subscribe(
        sentOrders => this.sentOrders = sentOrders,
        error => this.snack.open(error.error.message, undefined, { duration: 5000 })
      );
    }, () => {
      this.snack.open('Sie sind nicht eingeloggt!', undefined, { duration: 5000 });
      this.router.navigateByUrl('/');
    });
  }

}
