package co.veo.project.data.model.response

data class Movie(
    val adult: Boolean?,
    val backdrop_path: String?,
    val budget: Long?,
    val genres: List<Genre>,
    val homepage: String?,
    val id: Long?,
    val imdb_id: String?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Float?,
    val poster_path: String?,
    val production_companies: List<Company>?,
    val production_countries: List<Country>,
    val release_date: String?,
    val revenue: Long?,
    val runtime: Int?,
    val spoken_languages: List<Language>?,
    val status: String?,
    val tagline: String?,
    val title: String?,
    val video: Boolean?,
    val vote_average: Float?,
    val vote_count: Long?
)
