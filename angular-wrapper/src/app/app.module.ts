import { BrowserModule          }from '@angular/platform-browser';
import { NgModule               }from '@angular/core';
import { HttpClientModule       }from '@angular/common/http';
import { FormsModule            }from '@angular/forms';
import { AppRoutingModule       }from './app-routing.module';
import { AppComponent           }from './app.component';
import { NavigationBarComponent }from './navigation-bar/navigation-bar.component';
import { DashboardComponent     }from './dashboard/dashboard.component';
import { UsersTopComponent      }from './users-top/users-top.component';
import { UserBarComponent       }from './user-bar/user-bar.component';
import { SnippetFormComponent   }from './snippet-form/snippet-form.component';
import { UserDetailsComponent } from './user-details/user-details.component';
import { AuthComponent } from './auth/auth.component';
import { RegistrationComponent } from './registration/registration.component';

@NgModule({
  declarations: [
    AppComponent,
    NavigationBarComponent,
    DashboardComponent,
    UsersTopComponent,
    UserBarComponent,
    SnippetFormComponent,
    UserDetailsComponent,
    AuthComponent,
    RegistrationComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
