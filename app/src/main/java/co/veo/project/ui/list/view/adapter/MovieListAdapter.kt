package co.veo.project.ui.list.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.currentComposer
import androidx.recyclerview.widget.RecyclerView
import co.veo.project.R
import co.veo.project.data.model.response.Movie
import co.veo.project.databinding.ItemMovieBinding
import co.veo.project.ui.list.view.interfaces.OnMovieSelected
import co.veo.project.utility.Const
import co.veo.project.utility.Utility
import coil.load


class MovieListAdapter(
    private val movies: List<Movie>,
    private val listener: OnMovieSelected
) : RecyclerView.Adapter<MovieListAdapter.StoreHolder>() {
    private var lastView: StoreHolder? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreHolder {
        val inflatedView =
            LayoutInflater.from(Utility.getContext()).inflate(R.layout.item_movie, parent, false)
        return StoreHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: StoreHolder, position: Int) {
        with(holder) {
            val item = movies[position]

            binding.imgPoster.load(Const.IMAGE_BASE_URL + item.poster_path) {
                placeholder(R.drawable.ic_placeholder)
            }

            item.original_title?.let {
                binding.txtName.text = it
            } ?: run {
                binding.txtName.text = ""
            }

            item.release_date?.let {
                binding.txtReleaseDate.text = it
            } ?: run {
                binding.txtReleaseDate.text = ""
            }

            binding.cvItem.setOnClickListener {
                listener.onMovieClicked(item.id)
            }
        }
    }

    override fun getItemCount() = movies.size

    class StoreHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemMovieBinding.bind(itemView)
    }
}