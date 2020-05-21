import { Component, OnInit }from '@angular/core';
import { Subscription      }from 'rxjs';
import { SnippetService    }from '../snippet.service';
import { UserService       }from '../user.service';
import { Snippet           }from '../snippet';
import { File              }from '../file';
import { User, Anonymous   }from '../user';

@Component({
  selector: 'app-snippet-form',
  templateUrl: './snippet-form.component.html',
  styleUrls: ['./snippet-form.component.css'],
  providers: [UserService],
})
export class SnippetFormComponent implements OnInit {
  languages : string[];
  private _subscriptionUser = new Subscription();
  public model: Snippet = {
    id: 0,
    title: '',
    isPublic: true,
    createTime: '',
    userId: 1,
    files: [
      {
        id: 0,
        language: 'Text',
        title: '',
        content: '',
        changeTime: '',
      },
    ]
  }

  constructor(
    private snippetService : SnippetService,
    private userService : UserService,
  ) { }

  ngOnInit(): void {
    this.initLanguages();
    this.model.userId = this.userService.getCurrentUser().id;
  }

  onSubmit(): void {
    if (!this.model.title) {
      return;
    }
    for (var file of this.model.files) {
      if (!file.title || !file.language || !file.content) {
        return;
      }
    }

    this.model.id = this.userService.getCurrentUser().id;
    this.snippetService.createSnippet(this.model);
  }

  initLanguages() : void {
    this.snippetService.getLanguages()
    .subscribe( langs => this.languages = langs);
  }

  addEmptyFile() : void {
    this.model.files.push({
      id: 0,
      language: this.languages[0],
      title: '',
      content: '',
      changeTime: '',
    });
  }

  popLastFile() : void {
    if (this.model.files.length == 1) {
      return;
    }
    this.model.files.pop();
  }
}
