package co.veo.project

import co.veo.project.data.model.response.Movie
import co.veo.project.data.model.response.MovieList

class MovieRepositoryMock {
    fun getMovieList(
        mediaType: String,
        timeWindow: String,
        apiKey: String
    ): MovieList {
        val movie = Movie(
            id = 436270,
            original_title = "First Movie",
            poster_path = "/3zXceNTtyj5FLjwQXuPvLYK5YYL.jpg"
        )
        val movieList = arrayListOf<Movie>()
        movieList.add(movie)
        return MovieList(1, movieList, 100, 1000)
    }

    fun getMovieDetail(
        movieId: Long,
        apiKey: String
    ): Movie {
        return Movie()
    }
}