import { Component, OnInit } from '@angular/core';
import { MovieService } from '../../service/movie.service';
import { ActivatedRoute, Router } from '@angular/router';
import { MovieForm } from '../../model/movie-form';
import { DirectorService } from "../../../director/service/director.service";
import { Directors } from "../../../director/model/directors";

@Component({
  selector: 'app-movie-edit',
  templateUrl: './movie-edit.component.html',
  styleUrls: ['./movie-edit.component.css']
})
export class MovieEditComponent implements OnInit {

  /**
   * Movie's id.
   */
  uuid: string | undefined;

  /**
   * Single movie.
   */
  movie: MovieForm | undefined;

  /**
   * Single movie.
   */
  original: MovieForm | undefined;

  /**
   * Available movies.
   */
  directors: Directors | undefined;

  /**
   * @param movieService movie service
   * @param directorService director service
   * @param route activated route
   * @param router router
   */
  constructor(
    private movieService: MovieService,
    private directorService: DirectorService,
    private route: ActivatedRoute,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.directorService.getDirectors()
        .subscribe(directors => this.directors = directors);

      this.movieService.getMovie(params['uuid'])
        .subscribe(movie => {
          this.uuid = movie.id;
          this.movie = {
            name: movie.name,
            dateOfRelease: movie.dateOfRelease,
            time: movie.time,
            genre: movie.genre,
            director: movie.director.id
          };
          this.original = {...this.movie};
        });
    });
  }


  onSubmit(): void {
    this.movieService.putMovie(this.uuid!, this.movie!)
      .subscribe(() => this.router.navigate(['/movies']));
  }

}
