import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './routes/home/home.component';
import { ProductComponent } from './routes/product/product.component';
import { LoginComponent } from './routes/login/login.component';
import { RegisterComponent } from './routes/register/register.component';
import { UserComponent } from './routes/user/user.component';
import { CartComponent } from './routes/cart/cart.component';
import { OrderComponent } from './routes/order/order.component';


const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'product/:number', component: ProductComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'user', component: UserComponent },
  { path: 'cart', component: CartComponent },
  { path: 'order', component: OrderComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
