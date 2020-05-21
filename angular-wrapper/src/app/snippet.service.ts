import { Response                }from './response';
import { Snippet                 }from './snippet';
import { Injectable              }from '@angular/core';
import { HttpClient, HttpHeaders }from '@angular/common/http';
import { Router                  }from '@angular/router';
import { Observable, of          }from 'rxjs';
import { catchError              }from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SnippetService {
  private url = 'http://localhost:8080/api/snippet';
  private httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'})
  };

  constructor(
    private http: HttpClient,
    private router: Router,
  ) { }

  createSnippet(snippet : Snippet) {
    const body = {
      files: snippet.files,
      title: snippet.title,
      isPublic: snippet.isPublic,
      userId: snippet.userId,
    };

    var response :Response;
    this.http.post<Response>(this.url, body, this.httpOptions).subscribe(res => response = res);
    this.router.navigate(['/dashboard']);
  }

  getLanguages() : Observable<string[]> {
    return this.http.get<string[]>(`${this.url}/languages`);
  }

  getSnippets() : Observable<Snippet[]> {
    return this.http.get<Snippet[]>(`${this.url}/public`);
  }
}
