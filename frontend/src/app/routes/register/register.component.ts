import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { registerLocaleData } from '@angular/common';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  registerForm = new FormGroup({
    email: new FormControl('', Validators.email),
    password: new FormControl(''),
    firstName: new FormControl(''),
    lastName: new FormControl(''),
    street: new FormControl(''),
    postalCode: new FormControl(''),
    city: new FormControl('')
  });
  showPassword = false;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
  }

  register() {
    if (this.registerForm.valid) {
      this.userService.register(this.registerForm.value);
    }
  }

}
