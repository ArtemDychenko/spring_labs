import { Component, OnInit } from '@angular/core';
import { MovieService } from '../../service/movie.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MovieForm } from '../../model/movie-form';
import {Directors} from "../../../director/model/directors";
import {DirectorService} from "../../../director/service/director.service";


@Component({
  selector: 'app-movie-add',
  templateUrl: './movie-add.component.html',
  styleUrls: ['./movie-add.component.css']
})
export class MovieAddComponent implements OnInit  {


  uuid: string | undefined;

  directors: Directors | undefined;

  movie: MovieForm | undefined;

  /**
   * New Movie to be added.
   */
  newMovie: MovieForm = {
    name: '',
    dateOfRelease: 0,
    genre:'',
    time: 0,
    director: ''
  };

  /**
   * @param movieService Service for managing movies
   * @param router Angular router
   */
  constructor(
    private movieService: MovieService,
    private directorService: DirectorService,
    private route: ActivatedRoute,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.uuid = crypto.randomUUID();

    this.directorService.getDirectors()
      .subscribe(directors => this.directors = directors);
  }



  /**
   * Submits the form to add a new movie.
   */
  onSubmit(): void {
    this.movieService.putMovie(this.uuid!, this.newMovie!)
      .subscribe(() => this.router.navigate(['/movies']));
  }
}
