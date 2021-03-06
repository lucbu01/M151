import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { registerLocaleData, DatePipe } from '@angular/common';
import localeDeCh from '@angular/common/locales/de-CH';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HttpClientModule } from '@angular/common/http';

import { MatToolbarModule } from '@angular/material/toolbar';
import { MatButtonModule } from '@angular/material/button';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatCardModule } from '@angular/material/card';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatTableModule } from '@angular/material/table';

import { HomeComponent } from './routes/home/home.component';
import { ProductService } from './services/product.service';
import { ProductComponent } from './routes/product/product.component';
import { UserService } from './services/user.service';
import { LoginComponent } from './routes/login/login.component';
import { RegisterComponent } from './routes/register/register.component';
import { UserComponent } from './routes/user/user.component';
import { CartComponent } from './routes/cart/cart.component';
import { CartService } from './services/cart.service';
import { CheckoutComponent } from './routes/checkout/checkout.component';
import { CheckoutService } from './services/checkout.service';
import { OrderService } from './services/order.service';
import { OrderComponent } from './routes/order/order.component';
import { AdminOrdersComponent } from './routes/admin/orders/admin-orders.component';
import { AdminProductComponent } from './routes/admin/product/admin-product.component';

registerLocaleData( localeDeCh, 'de-CH');

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    ProductComponent,
    LoginComponent,
    RegisterComponent,
    UserComponent,
    CartComponent,
    CheckoutComponent,
    OrderComponent,
    AdminOrdersComponent,
    AdminProductComponent
  ],
  imports: [
    BrowserModule,
    ReactiveFormsModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    MatToolbarModule,
    MatIconModule,
    MatButtonModule,
    MatInputModule,
    MatFormFieldModule,
    MatTooltipModule,
    MatCardModule,
    MatSnackBarModule,
    MatTableModule
  ],
  providers: [
    ProductService,
    UserService,
    CartService,
    CheckoutService,
    OrderService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
