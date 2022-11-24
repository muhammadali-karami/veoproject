package co.veo.project.ui.detail.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import co.veo.project.R
import co.veo.project.data.model.response.Movie
import co.veo.project.data.remote.NetworkResource
import co.veo.project.databinding.FragmentDetailBinding
import co.veo.project.ui.detail.view_model.DetailMovieModel
import co.veo.project.utility.Const
import co.veo.project.utility.Utility
import coil.load
import dagger.hilt.android.AndroidEntryPoint


public const val ARG_MOVIE_ID = "movieId"

@AndroidEntryPoint
class DetailFragment : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private val viewModel: DetailMovieModel by viewModels()

    private var movieId: Long? = null

    companion object {
        /**
         * @param args is bundle.
         */
        @JvmStatic
        fun newInstance(args: Bundle?) =
            DetailFragment().apply {
                arguments = Bundle().apply {
                    args?.let {
                        movieId = args.getLong(ARG_MOVIE_ID, 0)
                    }
                }
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            movieId = it.getLong(ARG_MOVIE_ID, 0)
        }

        if (movieId == 0L) {
            // TODO go back
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        setObservers()
        getMovieDetail()
    }

    private fun setObservers() {
        observeMovieList()
    }

    private fun observeMovieList() {
        viewModel.movieDetailResult
            .observe(viewLifecycleOwner) { response ->
                when (response) {
                    is NetworkResource.Success -> {
                        hideLoader()
                        response.data?.let {
                            showMovieDetail(it)
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

    private fun getMovieDetail() {
        if (Utility.hasConnection(activity)) {
            viewModel.getMovieDetail(movieId!!)
        }
    }

    private fun showMovieDetail(movie: Movie) {
        binding.cvDetail.visibility = View.VISIBLE

        binding.imgPoster.load(Const.IMAGE_BASE_URL + movie.poster_path) {
            placeholder(R.drawable.ic_placeholder)
        }

        movie.original_title?.let {
            binding.txtName.text = it
        } ?: run {
            binding.txtName.visibility = View.GONE
        }

        movie.release_date?.let {
            binding.txtReleaseDate.text = String.format(getString(R.string.detail_release_date, it))
        } ?: run {
            binding.txtReleaseDate.visibility = View.GONE
        }

        movie.adult?.let {
            if (it) {
                binding.txtAdult.text = String.format(getString(R.string.detail_adult), getString(R.string.detail_adult_yes))
            } else {
                binding.txtAdult.text = String.format(getString(R.string.detail_adult), getString(R.string.detail_adult_no))
            }
        } ?: run {
            binding.txtAdult.visibility = View.GONE
        }

        movie.budget?.let {
            binding.txtBudget.text = String.format(getString(R.string.detail_budget, it))
        } ?: run {
            binding.txtBudget.visibility = View.GONE
        }

        movie.revenue?.let {
            binding.txtRevenue.text = String.format(getString(R.string.detail_revenue, it))
        } ?: run {
            binding.txtRevenue.visibility = View.GONE
        }

        movie.overview?.let {
            binding.txtOverview.text = it
        } ?: run {
            binding.txtOverview.visibility = View.GONE
        }
    }

    private fun showLoader() {
        binding.loader.visibility = View.VISIBLE
        binding.cvDetail.visibility = View.GONE
    }

    private fun hideLoader() {
        binding.loader.visibility = View.GONE
    }
}