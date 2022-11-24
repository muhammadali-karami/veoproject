package co.veo.project.ui.detail.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.veo.project.data.model.response.Movie
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
class DetailViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {

    private val _movieDetailResult = MutableLiveData<NetworkResource<Movie>>()
    val movieDetailResult: LiveData<NetworkResource<Movie>>
        get() = _movieDetailResult

    fun getMovieDetail(
        movieId: Long,
        apiKey: String? = Const.API_KEY
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _movieDetailResult.postValue(NetworkResource.Loading())
                val response = repository.getMovieDetail(
                    movieId,
                    apiKey!!
                )
                if (response.isSuccessful) {
                    response.body()?.let {
                        _movieDetailResult.postValue(NetworkResource.Success(it))
                    }
                } else {
                    _movieDetailResult.postValue(
                        NetworkResource.Error(
                            response.code(),
                            response.errorBody()!!
                        )
                    )
                }
            } catch (e: Exception) {
                Logger.e(e.message.toString())
                if (e is SocketTimeoutException) {
                    _movieDetailResult.postValue(
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