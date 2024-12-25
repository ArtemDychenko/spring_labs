import '../type/types.js'

/**
 * Service for making API calls for users characters.
 */
export class MovieService {

    /**
     * Fetches all movies.
     *
     * @returns {Promise<Movies>} completion promise with characters list
     */
    getMovies() {
        return fetch('/api/movies')
            .then(response => {
                if (response.ok) {
                    return response.json();
                }
                throw new Error("Network error.");
            });
    }

    /**
     * Removes single movie.
     *
     * @param {string} id movie id
     * @returns {Promise} completion promise
     */
    deleteMovie(id) {
        return fetch('/api/movies/' + id, {
            method: 'DELETE'
        })
            .then(response => {
                if (response.ok) {
                    return;
                }
                throw new Error("Network error.");
            });
    }

    /**
     * Fetches single movie detail.
     *
     * @param {string} id movie id
     * @returns {Promise<Movie>} completion promise with movie details
     */
    getMovie(id) {
        return fetch('/api/movies/' + id)
            .then(response => {
                if (response.ok) {
                    return response.json();
                }
                throw new Error("Network error.");
            });
    }

    /**
     *
     * @param {string} id movie id
     * @param {CharacterUpdate} request movie
     * @returns {Promise} completion promise
     */
    putMovie(id, request) {
        return fetch('/api/movies/' + id,
            {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(request)
            })
            .then(response => {
                if (response.ok) {
                    return;
                }
                throw new Error("Network error.");
            });
    }
}
