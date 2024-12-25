import '../type/types.js'


export class DirectorService {

    getDirectors() {
        return fetch('/api/directors')
            .then(response => {
                if (response.ok) {
                    return response.json();
                }
                throw new Error("Network error.");
            });
    }

}
