import {DataBinder} from '../../../data-binder.js';
import {UiUtils} from '../../../ui-utils.js';
import {MovieService} from '../../service/movie-service.js';
import {DirectorService} from '../../../director/service/director-service.js';
import '../../type/types.js'
import '../../../director/type/types.js'

window.addEventListener('load', () => {
    new MovieEditComponent(new DataBinder(), new UiUtils(), new MovieService(), new DirectorService()).onInit();
});


class MovieEditComponent {

    /**
     * Selected movies.
     *
     * @type {MovieUpdate}
     */
    movie;

    /**
     * All available Directors.
     *
     * @type {Directors}
     */
    directors;

    /**
     * movie service.
     *
     * @type {MovieService}
     */
    movieService;

    /**
     * Directors service.
     *
     * @type {DirectorService}
     */
    directorService;

    /**
     * Manages state of layout based on data model.
     *
     * @type {DataBinder}
     */
    dataBinder;

    /**
     * Utility component for UI related code.
     *
     * @type {UiUtils}
     */
    uiUtils;

    /**
     * @param {DataBinder} dataBinder manages state of layout based on data model
     * @param {UiUtils} uiUtils utility component for UI related code
     * @param {MovieService} movieService movie service
     * @param {DirectorService} directorService profession service
     */
    constructor(dataBinder, uiUtils, movieService, directorService) {
        this.dataBinder = dataBinder;
        this.uiUtils = uiUtils;
        this.movieService = movieService;
        this.directorService = directorService;
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
        let moviePromise = this.movieService.getMovie(this.getParameterByName('id'))
            .then(data => {
                this.movie = {
                    name: data.name,
                    dateOfRelease: data.date_of_release,
                    genre: data.genre,
                    time: data.time,
                    director: data.director.id
                };
            });

        let directorsPromise = this.directorService.getDirectors()
            .then(data => {
                this.directors = data;
            });

        Promise.all([moviePromise, directorsPromise])
            .then(() => {
                this.onRender();
            });
    }

    onRender() {
        this.dataBinder.render(this, document.getRootNode());
        this.uiUtils.initInputs();
    }

    onUpdateComponent() {
        this.dataBinder.update(this, document.getRootNode());
    }

    onSubmit() {
        this.onUpdateComponent();
        this.movieService.putMovie(this.getParameterByName('id'), this.movie)
            .then(() => {
                this.onInit();
            });
    }


    onReset() {
        setTimeout(this.uiUtils.initInputs, 0);
    }

}
