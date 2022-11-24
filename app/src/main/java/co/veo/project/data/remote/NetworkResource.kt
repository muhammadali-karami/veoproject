package co.veo.project.data.remote

import okhttp3.ResponseBody

sealed class NetworkResource<T> {
    data class Success< T>(val data: T?) : NetworkResource<T>()
    data class Error< T >(val code: Int?,val errorResponseBody: ResponseBody?= null) : NetworkResource<T>()
    class Loading<T> : NetworkResource<T>()
}
