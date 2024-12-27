import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Directors } from "../model/directors";
import { DirectorDetails } from "../model/director-details";
import { DirectorForm } from "../model/director-form";


@Injectable({
  providedIn: 'root'
})
export class DirectorService {


  constructor(private http: HttpClient) {

  }


  getDirectors(): Observable<Directors> {
    return this.http.get<Directors>('/api/directors');
  }

  getDirector(uuid: string): Observable<DirectorDetails> {
    return this.http.get<DirectorDetails>('/api/directors/' + uuid);
  }

  deleteDirector(uuid: string): Observable<any> {
    return this.http.delete('/api/directors/' + uuid);
  }

  putDirector(uuid: string, request: DirectorForm): Observable<any> {
    return this.http.put('/api/directors/' + uuid, request);
  }

}
