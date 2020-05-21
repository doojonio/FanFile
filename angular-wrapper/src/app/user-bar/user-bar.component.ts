import { Component, OnInit } from '@angular/core';
import { User, Anonymous } from '../user';
import { UserService } from '../user.service';
import { Subscription } from 'rxjs';

@Component({
  selector: 'app-user-bar',
  templateUrl: './user-bar.component.html',
  styleUrls: ['./user-bar.component.css'],
  providers: [UserService],
})
export class UserBarComponent implements OnInit {
  showAuth :Boolean = false;
  showRegister:Boolean=false;
  user : User;

  constructor(
    private userService: UserService
  ) {
    this.user = this.userService.getCurrentUser();
  }

  ngOnInit(): void { }

  login() :void {
    this.showAuth = true;
  }

  closeAuth() :void {
    this.showAuth = false;
  }

  register() :void {
    this.showRegister = true;
  }

  closeRegister() :void {
    this.showRegister = false;
  }

  logout() : void {
    this.userService.changeUser(Anonymous);
    this.user = Anonymous;
  }
}
