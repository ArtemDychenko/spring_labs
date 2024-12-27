/**
 * Represents single edition in form.
 */
export interface MovieForm {

  /**
   * Name of the movie.
   */
  name: string;

  /**
   * Date of release of the movie.
   */
  dateOfRelease: number;

  /**
   * Genre of the movie.
   */
  genre: string;

  /**
   * Time of the movie.
   */
  time: number;

  /**
   * Unique id identifying director of film.
   */
  director: string;
}
