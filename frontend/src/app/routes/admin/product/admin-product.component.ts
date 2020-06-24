import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subscription } from 'rxjs';
import { ActivatedRoute, Router } from '@angular/router';
import { ProductService } from 'src/app/services/product.service';
import { UserService } from 'src/app/services/user.service';
import { CartService } from 'src/app/services/cart.service';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-admin-product',
  templateUrl: './admin-product.component.html',
  styleUrls: ['./admin-product.component.scss']
})
export class AdminProductComponent implements OnInit, OnDestroy {

  subscriptions: Subscription[];
  productNumber: number;
  product: any;
  productForm = new FormGroup({
    name: new FormControl(''),
    description: new FormControl(''),
    price: new FormControl('', Validators.pattern(/^\d+($|[\.,]\d{1,2})$/))
  });

  initialize(productNumber: number) {
    setTimeout(() => {
      if (productNumber !== undefined) {
        if (this.userService.isAdmin()) {
          this.productNumber = productNumber;
          this.productService.getProductByNumber(productNumber).subscribe(product => {
            this.product = product;
            this.productForm.setValue({
              name: product.name,
              description: product.description,
              price: `${product.price}`.replace(',', '.')
            });
          });
        } else {
          this.router.navigateByUrl(`/product/${productNumber}`);
        }
      } else if (this.userService.isAdmin()) {
        this.product = undefined;
        this.productNumber = undefined;
        this.productForm.setValue({ name: '', description: '', price: ''});
      } else {
        this.router.navigateByUrl(`/`);
      }
    });
  }

  constructor(private route: ActivatedRoute,
              private router: Router,
              private productService: ProductService,
              public userService: UserService,
              public cartService: CartService,
              private snack: MatSnackBar) { }

  ngOnInit(): void {
    this.subscriptions = [
      this.route.params.subscribe(params => this.initialize(params.number))
    ];
  }

  ngOnDestroy(): void {
    this.subscriptions.forEach(subscription => subscription.unsubscribe());
  }

  saveProduct() {
    if (this.productForm.valid) {
      try {
        const name = this.productForm.value.name;
        const description = this.productForm.value.description;
        const price = parseFloat(this.productForm.value.price);
        const newProduct = { name, description, price };
        if (this.product) {
          this.productService.updateProduct(this.product.number, newProduct);
        } else {
          this.productService.createProduct(newProduct).subscribe(newProductNumber => {
            this.snack.open('Das Produkt wurde erstellt', undefined, { duration: 5000 });
            this.router.navigateByUrl(`admin/product/${newProductNumber}`);
          }, error => this.snack.open(error.error.message, undefined, { duration: 5000 }));
        }
      } catch (e) {
        console.log(e);
      }
    }
  }

  deleteProduct() {
    if (this.product) {
      this.productService.deleteProduct(this.product.number);
    }
  }
}
