package domain.movie.model

import data.movie.model.response.MovieItem
import data.movie.model.response.MovieRecomendationItem


fun MovieItem.toObject(): Movie {
    return Movie(
        overview = overview.orEmpty(),
        title = title.orEmpty(),
        genreIds = genreIds.orEmpty(),
        id = id
    )
}

fun MovieRecomendationItem.toObject(): MovieRecomendations {
    return MovieRecomendations(
        title = title.orEmpty()
    )
}