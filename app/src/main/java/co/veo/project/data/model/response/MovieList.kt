package co.veo.project.data.model.response

data class MovieList(
    val page: Int?,
    val results: List<Movie>?,
    val total_pages: Int?,
    val total_results: Long?
)
