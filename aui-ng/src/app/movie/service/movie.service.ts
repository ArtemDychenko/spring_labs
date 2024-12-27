import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs";
import { Movies } from "../model/movies";
import { MovieDetails } from "../model/movie-details";
import { MovieForm } from "../model/movie-form";


@Injectable()
export class MovieService {

  /**
   * @param http HTTP client
   */
  constructor(private http: HttpClient) {

  }

  getMovies(): Observable<Movies> {
    return this.http.get<Movies>('/api/movies');
  }

  getMovie(uuid: string): Observable<MovieDetails> {
    return this.http.get<MovieDetails>('/api/movies/' + uuid);
  }

  deleteMovie(uuid: string): Observable<any> {
    return this.http.delete('/api/movies/' + uuid);
  }

  putMovie(uuid: string, request: MovieForm): Observable<any> {
    return this.http.put('/api/movies/' + uuid, request);
  }

}
