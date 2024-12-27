import { Component, OnInit } from '@angular/core';
import { MovieService } from "../../service/movie.service";
import { ActivatedRoute, Router } from "@angular/router";
import { MovieDetails } from "../../model/movie-details";
import {Movie} from "../../model/movie";

@Component({
  selector: 'app-movie-view',
  templateUrl: './movie-view.component.html',
  styleUrls: ['./movie-view.component.css']
})
export class MovieViewComponent implements OnInit {


  movie: MovieDetails | undefined;

  constructor(private service: MovieService, private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.service.getMovie(params['uuid'])
        .subscribe(movie => this.movie = movie)
    });
  }

  onDelete(movie: Movie): void {
    this.service.deleteMovie(movie.id).subscribe(() => this.ngOnInit());
  }

}
