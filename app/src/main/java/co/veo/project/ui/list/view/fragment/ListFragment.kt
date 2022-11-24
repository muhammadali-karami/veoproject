package co.veo.project.ui.list.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.NavHostFragment
import co.veo.project.R
import co.veo.project.data.enum.MediaType
import co.veo.project.data.enum.TimeWindowType
import co.veo.project.data.model.response.MovieList
import co.veo.project.data.remote.NetworkResource
import co.veo.project.databinding.FragmentListBinding
import co.veo.project.ui.detail.view.fragment.DetailFragment
import co.veo.project.ui.list.view.adapter.MovieListAdapter
import co.veo.project.ui.list.view.interfaces.OnMovieSelected
import co.veo.project.ui.list.view_model.ListViewModel
import co.veo.project.utility.Utility
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ListFragment : Fragment(), OnMovieSelected {

    private lateinit var binding: FragmentListBinding
    private val viewModel: ListViewModel by viewModels()

    companion object {
        /**
         * @param args is bundle.
         */
        @JvmStatic
        fun newInstance(args: Bundle?) =
            ListFragment().apply {
                arguments = Bundle().apply {
                    args?.let {}
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        setObservers()
        getMovieList()
    }

    private fun setObservers() {
        observeMovieList()
    }

    private fun observeMovieList() {
        viewModel.movieListResult
            .observe(viewLifecycleOwner) { response ->
                when (response) {
                    is NetworkResource.Success -> {
                        hideLoader()
                        response.data?.let {
                            showMovieList(it)
                        }
                    }
                    is NetworkResource.Error -> {
                        hideLoader()
                        response.errorResponseBody?.let {
                            // TODO show server error (for example inside a dialog)
                        }
                    }
                    is NetworkResource.Loading -> {
                        showLoader()
                    }
                }
            }
    }

    private fun getMovieList() {
        if (Utility.hasConnection(activity)) {
            viewModel.getMovieList(MediaType.MOVIE.type, TimeWindowType.DAY.type)
        }
    }

    private fun showMovieList(movieList: MovieList) {
        movieList.results?.let {
            binding.rvList.visibility = View.VISIBLE
            val adapter = MovieListAdapter(movieList.results, this)
            binding.rvList.adapter = adapter
        }
    }

    override fun onMovieClicked(movieId: Long?) {
        movieId?.let {
            val navHostFragment = activity?.supportFragmentManager?.findFragmentById(R.id.navHostFragment) as NavHostFragment
            val navController = navHostFragment.navController

            val bundle = Bundle()
            bundle.putLong("movieId", it)
            DetailFragment.newInstance(bundle)

            navController.navigate(R.id.action_listFragment_to_detailFragment, bundle)
        }
    }

    private fun showLoader() {
        binding.loader.visibility = View.VISIBLE
        binding.rvList.visibility = View.GONE
    }

    private fun hideLoader() {
        binding.loader.visibility = View.GONE
    }
}