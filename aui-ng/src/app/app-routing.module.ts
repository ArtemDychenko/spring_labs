import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MovieListComponent } from "./movie/view/movie-list/movie-list.component";
import { MovieViewComponent } from "./movie/view/movie-view/movie-view.component";
import { MovieEditComponent } from "./movie/view/movie-edit/movie-edit.component";
import { DirectorListComponent } from "./director/view/director-list/director-list.component";
import { DirectorViewComponent } from "./director/view/director-view/director-view.component";
import { DirectorEditComponent } from "./director/view/director-edit/director-edit.component";
import {DirectorAddComponent} from "./director/view/director-add/director-add.component";

/**
 * All available routes.
 */
const routes: Routes = [
  {
    component: MovieListComponent,
    path: "movies"
  },
  {
    component: MovieViewComponent,
    path: "movies/:uuid"
  },
  {
    component: MovieEditComponent,
    path: "movies/:uuid/edit"
  },
  {
    component: DirectorListComponent,
    path: "directors"
  },
  {
    component: DirectorViewComponent,
    path: "directors/:uuid"
  },
  {
    component: DirectorEditComponent,
    path: "directors/:uuid/edit"
  },
  {
    component: DirectorAddComponent,
    path: "directors/:uuid/add"
  }
];

/**
 * Global routing module.
 */
@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {

}
