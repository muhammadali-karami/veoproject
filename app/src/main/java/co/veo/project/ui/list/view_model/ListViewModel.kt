package co.veo.project.ui.list.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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

    private val _movieResult = MutableLiveData<NetworkResource<MovieList>>()
    val movieResult: LiveData<NetworkResource<MovieList>>
        get() = _movieResult

    fun getMovieList(
        mediaType: String,
        timeWindow: String,
        apiKey: String? = Const.API_KEY
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                _movieResult.postValue(NetworkResource.Loading())
                val response = repository.getMovieList(
                    mediaType,
                    timeWindow,
                    apiKey!!
                )
                if (response.isSuccessful) {
                    response.body()?.let {
                        _movieResult.postValue(NetworkResource.Success(it))
                    } ?: run {
                        // TODO
                    }
                } else {
                    _movieResult.postValue(
                        NetworkResource.Error(
                            response.code(),
                            response.errorBody()!!
                        )
                    )
                }
            } catch (e: Exception) {
                Logger.e(e.message.toString())
                if (e is SocketTimeoutException) {
                    _movieResult.postValue(
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