import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductService } from 'src/app/services/product.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-product',
  templateUrl: './product.component.html',
  styleUrls: ['./product.component.scss']
})
export class ProductComponent implements OnInit, OnDestroy {
  subscriptions: Subscription[];
  productNumber: number;
  product: any;

  initialize(productNumber: number) {
    this.productNumber = productNumber;
    this.productService.getProductByNumber(productNumber).subscribe(product => this.product = product);
  }

  constructor(private route: ActivatedRoute, private productService: ProductService) { }

  ngOnInit(): void {
    this.subscriptions = [
      this.route.params.subscribe(params => this.initialize(params.number))
    ];
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

}
