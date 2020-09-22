package domain.movie.model


data class Movie(
	val overview: String? = null,
	val title: String? = null,
	val genreIds: List<Int>? = null,
	val id: Int? = null
)
