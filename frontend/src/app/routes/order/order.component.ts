import { Component, OnInit, OnDestroy } from '@angular/core';
import { OrderService } from 'src/app/services/order.service';
import { UserService } from 'src/app/services/user.service';
import { Subscription } from 'rxjs';
import { ActivatedRoute } from '@angular/router';
import { Location } from '@angular/common';

@Component({
  selector: 'app-order',
  templateUrl: './order.component.html',
  styleUrls: ['./order.component.scss']
})
export class OrderComponent implements OnInit, OnDestroy {

  constructor(private route: ActivatedRoute,
              private location: Location,
              private orderService: OrderService,
              public userService: UserService) { }

  subscriptions: Subscription[];

  orderNumber: number;
  order: any;
  displayedColumns = [ 'productNumber', 'productName', 'productPrice', 'count', 'totalPrice' ];

  initialize(orderNumber: number) {
    this.orderNumber = orderNumber;
    this.orderService.getOrderDetails(orderNumber).subscribe(order => this.order = order, () => this.location.back());
  }

  ngOnInit(): void {
    this.subscriptions = [
      this.route.params.subscribe(params => this.initialize(params.number))
    ];
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  setOrderStatusSent() {
    this.orderService.setOrderStatusSent(this.orderNumber, () => this.initialize(this.orderNumber));
  }
}
