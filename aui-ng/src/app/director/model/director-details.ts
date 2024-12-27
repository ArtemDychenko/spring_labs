import { Movie } from "../../movie/model/movie";

export interface DirectorDetails {

  /**
   * Unique id identifying director.
   */
  id: string;

  /**
   * Name of the director.
   */
  name: string;

  /**
   * Year of birth of the director.
   */
  yearOfBirth: number;


  movies: Movie[];
}
