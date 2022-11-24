package co.veo.project.data.repository

import co.veo.project.data.remote.ApiService
import retrofit2.http.Path
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(private val apiService: ApiService) {

    suspend fun getMovieList(
        mediaType: String,
        timeWindow: String,
        apiKey: String
    ) = apiService.getMovieList(
        mediaType,
        timeWindow,
        apiKey
    )

    suspend fun getMovieDetail(
        movieId: Long,
        apiKey: String
    ) = apiService.getMovieDetail(
        movieId,
        apiKey
    )
}