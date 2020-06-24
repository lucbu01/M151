import { Component, OnInit } from '@angular/core';
import { CheckoutService } from 'src/app/services/checkout.service';
import { Router } from '@angular/router';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-checkout',
  templateUrl: './checkout.component.html',
  styleUrls: ['./checkout.component.scss']
})
export class CheckoutComponent implements OnInit {

  finalOrder = false;
  order: any;
  displayedColumns = [ 'productNumber', 'productName', 'productPrice', 'count', 'totalPrice' ];

  constructor(private checkoutService: CheckoutService, private router: Router, private snack: MatSnackBar) { }

  ngOnInit(): void {
    this.checkoutService.checkoutPreview().subscribe(orderPreview => {
      this.order = orderPreview;
      this.finalOrder = false;
    }, error => {
      this.snack.open(error.error.message, undefined, { duration: 5000 });
      this.router.navigateByUrl('/');
    });
  }

  cancelCheckoutPreview() {
    this.checkoutService.cancelCheckoutPreview();
    this.router.navigateByUrl('/cart');
  }

  orderCheckoutPreview() {
    this.checkoutService.orderCheckoutPreview().subscribe(orderPreview => {
      this.order = orderPreview;
      this.finalOrder = true;
    }, error => {
      this.snack.open(error.error.message, undefined, { duration: 5000 });
    });
  }
}
