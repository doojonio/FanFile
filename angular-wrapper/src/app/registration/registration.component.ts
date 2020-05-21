import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { AuthData } from '../auth-data';
import { User } from '../user';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {
  model:AuthData = {
    login: '',
    password: '',
  };

  public fetchedUser: User;

  constructor(
    private userService : UserService,
  ) { }

  ngOnInit(): void {
    this.userService.currentUser.subscribe(
      u => this.fetchedUser = u
    );
  }

  register() : void {
    if (!this.model.password || !this.model.login) {
      return;
    }

    this.userService.registerUser(this.model);
  }
}
