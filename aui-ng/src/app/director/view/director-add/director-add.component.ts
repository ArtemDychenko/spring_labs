import { Component, OnInit } from '@angular/core';
import { DirectorService } from '../../service/director.service';
import { ActivatedRoute, Router } from '@angular/router';
import { DirectorForm } from '../../model/director-form';


@Component({
  selector: 'app-director-add',
  templateUrl: './director-add.component.html',
  styleUrls: ['./director-add.component.css']
})
export class DirectorAddComponent implements OnInit  {


  uuid: string | undefined;

  /**
   * New director to be added.
   */
  newDirector: DirectorForm = {
    name: '',
    yearOfBirth: 0
  };

  /**
   * @param directorService Service for managing directors
   * @param router Angular router
   */
  constructor(
    private directorService: DirectorService,
    private route: ActivatedRoute,
    private router: Router
  ) {
  }

  ngOnInit() {
    this.uuid = crypto.randomUUID();
  }



  /**
   * Submits the form to add a new director.
   */
  onSubmit(): void {
    this.directorService.putDirector(this.uuid!, this.newDirector!)
      .subscribe(() => this.router.navigate(['/directors']));
  }
}
