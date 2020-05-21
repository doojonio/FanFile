import { Component, OnInit } from '@angular/core';
import { AuthData } from '../auth-data';
import { UserService } from '../user.service';
import { User } from '../user';

@Component({
  selector: 'app-auth',
  templateUrl: './auth.component.html',
  styleUrls: ['./auth.component.css']
})
export class AuthComponent implements OnInit {
  model:AuthData = {
    login: '',
    password: '',
  };

  public fetchedUser:User;

  constructor(
    private userService: UserService,
  ) { }

  ngOnInit(): void {
    this.userService.currentUser.subscribe(
      u => this.fetchedUser = u
    );
  }

  login(): void {
    if (!this.model.password || !this.model.login) {
      return;
    }

    this.userService.authUser(this.model)
  }
}
