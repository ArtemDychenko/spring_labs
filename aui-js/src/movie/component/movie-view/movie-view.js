import {DataBinder} from '../../../data-binder.js';
import {MovieService} from '../../service/movie-service.js';
import '../../type/types.js'
import '../../../director/type/types.js'

window.addEventListener('load', () => {
    new MovieViewComponent(new DataBinder(), new MovieService()).onInit();
});

/**
 * Represents component listing all characters.
 */
class MovieViewComponent {

    /**
     * Selected characters.
     *
     * @type {MovieDetails}
     */
    movie;

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
     * Returns value for query param.
     *
     * @param {string} name name of the query param
     * @returns {string} query param value
     */
    getParameterByName(name) {
        return new URLSearchParams(window.location.search).get(name);
    }

    /**
     * Called when component is initialized.
     */
    onInit() {
        this.movieService.getMovie(this.getParameterByName('id'))
            .then(data => {
                this.movie = data;
                this.onRender();
            });
    }

    /**
     * Called when component data should be bound to layout.
     */
    onRender() {
        this.dataBinder.render(this, document.getRootNode());
    }

}
