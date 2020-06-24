import { Component, OnInit } from '@angular/core';
import { CartService } from 'src/app/services/cart.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.scss']
})
export class CartComponent implements OnInit {

  displayedColumns = [ 'productNumber', 'productName', 'productPrice', 'count', 'totalPrice' ];
  cart: any;

  constructor(public cartService: CartService, private router: Router) { }

  ngOnInit(): void {
    this.cartService.get().subscribe(cart => this.cart = cart, () => {
      this.cartService.notLoggedIn();
      this.router.navigateByUrl('/');
    });
    this.cartService.onChange.subscribe(cart => this.cart = cart);
  }

  add(position: any) {
    this.cartService.add(position.product.number);
  }

  remove(position: any) {
    this.cartService.remove(position.product.number);
  }

  set(position: any, event: any) {
    this.cartService.set(position.product.number, event.target.value);
  }

  removePositon(position: any) {
    this.cartService.removePosition(position.product.number);
  }
}
