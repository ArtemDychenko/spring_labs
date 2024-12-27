import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FooterComponent } from './component/footer/footer.component';
import { HeaderComponent } from './component/header/header.component';
import { NavComponent } from './component/nav/nav.component';
import { MainComponent } from './component/main/main.component';
import { HttpClientModule } from "@angular/common/http";
import { MovieListComponent } from './movie/view/movie-list/movie-list.component';
import { MovieService } from './movie/service/movie.service';
import { MovieViewComponent } from './movie/view/movie-view/movie-view.component';
import { MovieEditComponent } from './movie/view/movie-edit/movie-edit.component';
import { FormsModule } from "@angular/forms";

/**
 * Application main module.
 */
@NgModule({
  declarations: [
    AppComponent,
    FooterComponent,
    HeaderComponent,
    NavComponent,
    MainComponent,
    MovieListComponent,
    MovieViewComponent,
    MovieEditComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [
    MovieService,
  ],
  bootstrap: [
    AppComponent
  ]
})
export class AppModule {

}
