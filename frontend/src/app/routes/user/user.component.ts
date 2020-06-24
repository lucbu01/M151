import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/services/user.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

  user: any;

  constructor(public userService: UserService, private snack: MatSnackBar, private router: Router) { }

  ngOnInit(): void {
    this.userService.getInfo().subscribe(info => this.user = info, () => {
      this.snack.open('Sie sind nicht eingeloggt!');
      this.router.navigateByUrl('/');
    });
  }

}
