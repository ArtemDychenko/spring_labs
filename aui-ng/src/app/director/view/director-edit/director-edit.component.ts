import { Component, OnInit } from '@angular/core';
import { DirectorService } from '../../service/director.service';
import { ActivatedRoute, Router } from '@angular/router';
import { DirectorForm } from '../../model/director-form';
//import { MovieService } from "../../../movie/service/movie.service";
import { Directors } from "../../../director/model/directors";

@Component({
  selector: 'app-director-edit',
  templateUrl: './director-edit.component.html',
  styleUrls: ['./director-edit.component.css']
})
export class DirectorEditComponent implements OnInit {

  /**
   * Movie's id.
   */
  uuid: string | undefined;

  /**
   * Single movie.
   */
  director: DirectorForm | undefined;


  /**
   * Available movies.
   */
  directors: Directors | undefined;

  /**
   * @param directorService movie service
   * @param directorService director service
   * @param route activated route
   * @param router router
   */
  constructor(
    private directorService: DirectorService,
    private route: ActivatedRoute,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      this.directorService.getDirectors()
        .subscribe(directors => this.directors = directors);

      this.directorService.getDirector(params['uuid'])
        .subscribe(director => {
          this.uuid = director.id;
          this.director = {
            name: director.name,
            yearOfBirth: director.yearOfBirth,
            // director: movie.director.id
          };
          this.director = {...this.director};
        });
    });
  }


  onSubmit(): void {
    this.directorService.putDirector(this.uuid!, this.director!)
      .subscribe(() => this.router.navigate(['/directors']));
  }

}
