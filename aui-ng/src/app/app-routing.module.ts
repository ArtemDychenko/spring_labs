import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { MovieListComponent } from "./movie/view/movie-list/movie-list.component";
import { MovieViewComponent } from "./movie/view/movie-view/movie-view.component";
import { MovieEditComponent } from "./movie/view/movie-edit/movie-edit.component";

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
  }
  ,
  {
    component: MovieEditComponent,
    path: "movies/:uuid/edit"
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
