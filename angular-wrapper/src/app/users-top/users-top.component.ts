import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { User } from '../user';

@Component({
  selector: 'app-users-top',
  templateUrl: './users-top.component.html',
  styleUrls: ['./users-top.component.css']
})
export class UsersTopComponent implements OnInit {
  users: User[];

  constructor(
    private userService: UserService,
  ) { }

  ngOnInit(): void {
    this.initUsers();
  }

  initUsers(): void {
    this.userService.getTopUsers()
    .subscribe(users => {
      users.sort((a, b) => {
        if (a.snippetAmount < b.snippetAmount) {
          return 1;
        } else { return -1 }
      });

      this.users = users;
    });
  }
}
