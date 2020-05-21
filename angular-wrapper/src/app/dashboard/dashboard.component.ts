import { Component, OnInit }from '@angular/core';
import { Snippet           }from '../snippet';
import { SnippetService    }from '../snippet.service';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  snippets : Snippet[];

  constructor(
    private snippetService: SnippetService,
  ) { }

  ngOnInit(): void {
    this.snippetService.getSnippets().subscribe(s => this.snippets = s);
  }

}
