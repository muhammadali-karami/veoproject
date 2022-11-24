package co.veo.project.data.model.response

data class Movie(
    val adult: Boolean? = null,
    val backdrop_path: String? = null,
    val budget: Long? = null,
    val genres: List<Genre>? = null,
    val homepage: String? = null,
    val id: Long? = null,
    val imdb_id: String? = null,
    val original_language: String? = null,
    val original_title: String? = null,
    val overview: String? = null,
    val popularity: Float? = null,
    val poster_path: String? = null,
    val production_companies: List<Company>? = null,
    val production_countries: List<Country>? = null,
    val release_date: String? = null,
    val revenue: Long? = null,
    val runtime: Int? = null,
    val spoken_languages: List<Language>? = null,
    val status: String? = null,
    val tagline: String? = null,
    val title: String? = null,
    val video: Boolean? = null,
    val vote_average: Float? = null,
    val vote_count: Long? = null
)
