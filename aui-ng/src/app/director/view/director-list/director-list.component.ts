import { Component, OnInit } from '@angular/core';
import { DirectorService } from "../../service/director.service";
import { Directors } from "../../model/directors";
import { Director } from "../../model/director";

@Component({
  selector: 'app-director-list',
  templateUrl: './director-list.component.html',
  styleUrls: ['./director-list.component.css']
})
export class DirectorListComponent implements OnInit{

  constructor(private service: DirectorService) {
  }

  directors: Directors | undefined;

  ngOnInit(): void {
    this.service.getDirectors().subscribe(directors => this.directors = directors);
  }

  onDelete(director: Director): void {
    this.service.deleteDirector(director.id).subscribe(() => this.ngOnInit());
  }



}
