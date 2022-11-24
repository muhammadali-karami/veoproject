package co.veo.project.ui.list.view_model

import androidx.lifecycle.ViewModel
import co.veo.project.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val repository: MovieRepository) : ViewModel() {

}