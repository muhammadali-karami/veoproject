package co.veo.project.ui.list.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.veo.project.data.enum.MediaType
import co.veo.project.data.enum.TimeWindowType
import co.veo.project.data.model.response.MovieList
import co.veo.project.data.remote.NetworkResource
import co.veo.project.data.repository.MovieRepository
import co.veo.project.utility.Const
import co.veo.project.utility.Logger
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {

    private val _movieListResult = MutableLiveData<NetworkResource<MovieList>>()
    val movieListResult: LiveData<NetworkResource<MovieList>>
        get() = _movieListResult

    init {
        // TODO paging 3 should be used for pagination in this viewmodel
        getMovieList(MediaType.MOVIE.type, TimeWindowType.DAY.type)
    }

    fun getMovieList(
        mediaType: String,
        timeWindow: String,
        apiKey: String? = Const.API_KEY
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _movieListResult.postValue(NetworkResource.Loading())
                val response = repository.getMovieList(
                    mediaType,
                    timeWindow,
                    apiKey!!
                )
                if (response.isSuccessful) {
                    response.body()?.let {
                        _movieListResult.postValue(NetworkResource.Success(it))
                    }
                } else {
                    _movieListResult.postValue(
                        NetworkResource.Error(
                            response.code(),
                            response.errorBody()!!
                        )
                    )
                }
            } catch (e: Exception) {
                Logger.e(e.message.toString())
                if (e is SocketTimeoutException) {
                    _movieListResult.postValue(
                        NetworkResource.Error(
                            HttpURLConnection.HTTP_GATEWAY_TIMEOUT,
                            null
                        )
                    )
                }
            }
        }
    }
}