import {DataBinder} from '../../../data-binder.js';
import {MovieService} from '../../service/movie-service.js';
import '../../type/types.js'
import '../../../director/type/types.js'

window.addEventListener('load', () => {
    new MovieListComponent(new DataBinder(), new MovieService()).onInit();
});

/**
 * Represents component listing all characters.
 */
class MovieListComponent {

    /**
     * Manages state of layout based on data model.
     *
     * @type Movies
     */
    movies;

    /**
     * Character service.
     *
     * @type {MovieService}
     */
    movieService;

    /**
     * Manages state of layout based on data model.
     *
     * @type {DataBinder}
     */
    dataBinder;

    /**
     * @param {DataBinder} dataBinder manages state of layout based on data model
     * @param {MovieService} movieService character service
     */
    constructor(dataBinder, movieService) {
        this.dataBinder = dataBinder;
        this.movieService = movieService;
    }

    /**
     * Called when component is initialized.
     */
    onInit() {
        this.movieService.getMovies()
            .then(data => {
                this.movies = data;
                this.onRender();
            });
    }

    /**
     * Called when component data should be bound to layout.
     */
    onRender() {
        this.dataBinder.render(this, document.getRootNode());
    }

    /**
     * Called when changes in component data should be detected.
     */
    onDetectChanges() {
        this.dataBinder.detectChanges(this, document.getRootNode());
    }

    // noinspection JSUnusedGlobalSymbols
    /**
     * Removes selected movie.
     *
     * @param {Movie} movie
     */
    onDelete(movie) {
        this.movieService.deleteMovie(movie.id)
            .then(() => {
                let index = this.movies.movies.findIndex(item => item === movie);
                if (index > -1) {
                    this.movies.movies.splice(this.movies.movies.findIndex(item => item === movie), 1);
                    this.onDetectChanges();
                }
            });
    }

}
