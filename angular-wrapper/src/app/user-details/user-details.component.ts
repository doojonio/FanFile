import { ActivatedRoute    }from '@angular/router';
import { Component, OnInit }from '@angular/core';
import { UserService       }from '../user.service';
import { User              }from '../user';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrls: ['./user-details.component.css']
})
export class UserDetailsComponent implements OnInit {
  public user : User;

  constructor(
    private route: ActivatedRoute,
    private userService: UserService,
  ) { }

  ngOnInit(): void {
    this.initUser();
  }

  initUser() :void {
    const id = this.route.snapshot.paramMap.get('id');
    this.userService.getUser(id).subscribe(u => this.user = u);
  }

}
