import { Component, OnInit } from '@angular/core';
import { DirectorService } from "../../service/director.service";
import { ActivatedRoute, Router } from "@angular/router";
import { DirectorDetails } from "../../model/director-details";
import {Director} from "../../model/director";
import {Movie} from "../../../movie/model/movie";

@Component({
  selector: 'app-director-view',
  templateUrl: './director-view.component.html',
  styleUrls: ['./director-view.component.css']
})
export class DirectorViewComponent implements OnInit {

  director: DirectorDetails | undefined;


  constructor(private service: DirectorService, private route: ActivatedRoute, private router: Router) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      const uuid = params['uuid'];

      // Pobieranie szczegółów dyrektora
      this.service.getDirector(uuid).subscribe(director => {
        this.director = director;

        // Po pobraniu szczegółów pobieramy filmy
        this.service.getDirectorMovies(uuid).subscribe(movies => {
          if (this.director) {
            this.director.movies = movies; // Przypisanie do pola movies w DirectorDetails
          }
        });
      });
    });
  }


  onDelete(director: Director): void {
    this.service.deleteDirector(director.id).subscribe(() => this.ngOnInit());
  }

  // onAdd(): void {
  //   this.service.putDirector(this.uuid!, this.director!)
  //     .subscribe(() => this.router.navigate(['/directors']));
  // }

}
