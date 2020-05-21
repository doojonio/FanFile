import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { BehaviorSubject, Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';

import { User, Anonymous } from './user';
import { AuthData } from './auth-data';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  private url = 'http://localhost:8080/api/user';
  private httpOptions = {
    headers: new HttpHeaders({'Content-Type': 'application/json'}),
  };

  public currentUser = new BehaviorSubject<User>(Anonymous);

  constructor(
    private http: HttpClient,
  ) {}

  getTopUsers() : Observable<User[]> {
    return this.http.get<User[]>(`${this.url}/all`).pipe(
      catchError(this.handleError<User[]>('getTopUsers', []))
    );
  }

  changeUser(user: User) {
    this.currentUser.next(user);
    window.localStorage.setItem('User', JSON.stringify(user));
  }

  getCurrentUser() : User {
    return JSON.parse(window.localStorage.getItem('User'));
  }

  getUser(id: string) : Observable<User> {
    const url = `${this.url}?id=${id}`;
    return this.http.get<User>(url).pipe(
      catchError(this.handleError<User>('getUser')),
    );
  }

  authUser(authData:AuthData) :void {
    const url = `${this.url}/login`;
    var authorizedUser : User;

    this.http.post<User>(url, authData, this.httpOptions).subscribe(
      u => this.changeUser(u)
    );
  }

  registerUser(registerData:AuthData) : void {
    var registeredUser : User;

    this.http.post<User>(this.url, registerData, this.httpOptions).subscribe(
      u => this.changeUser(u)
    );
  }

  handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {
      console.error(error);
      return of(result as T);
    }
  }
}
