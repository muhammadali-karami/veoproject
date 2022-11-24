package co.veo.project.data.remote

import co.veo.project.data.model.response.MovieList
import com.google.android.gms.common.internal.safeparcel.SafeParcelable.Param
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiService {

    @GET("trending/{mediaType}/{timeWindow}")
    suspend fun getMovieList(
        @Path("mediaType") mediaType: String,
        @Path("timeWindow") timeWindow: String,
        @Query("api_key") apiKey: String
    ) : Response<MovieList>
}