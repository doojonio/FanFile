import { NgModule             }from '@angular/core';
import { Routes, RouterModule }from '@angular/router';
import { UsersTopComponent    }from './users-top/users-top.component';
import { SnippetFormComponent }from './snippet-form/snippet-form.component';
import { DashboardComponent   }from './dashboard/dashboard.component';
import { UserDetailsComponent }from './user-details/user-details.component';


const routes: Routes = [
  { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'top', component: UsersTopComponent },
  { path: 'new', component: SnippetFormComponent },
  { path: 'user/:id', component: UserDetailsComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
