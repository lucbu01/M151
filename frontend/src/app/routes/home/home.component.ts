import { Component, OnInit } from '@angular/core';
import { ProductService } from 'src/app/services/product.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {
  products: any[];

  constructor(private productService: ProductService, protected userService: UserService) { }

  ngOnInit(): void {
    this.productService.getProductList().subscribe(prdList => this.products = prdList);
  }

}
