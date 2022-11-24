package co.veo.project.data.repository

import co.veo.project.data.remote.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(private val apiService: ApiService) {

}