import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { EditComponent } from './edit/edit.component';
import { LoginComponent } from './login/login.component';
import { NewComponent } from './new/new.component';
import { RecipesComponent } from './recipes/recipes.component';

const routes: Routes = [
  {path: 'edit/:id', component: EditComponent},
  {path: 'login', component: LoginComponent},
  {path: 'recipes/new', component: NewComponent},
  {path: 'recipes', component: RecipesComponent},
  {path: "", pathMatch: "full", redirectTo: "/recipes"}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
