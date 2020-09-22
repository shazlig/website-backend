package domain.movie

import domain.movie.model.Movie
import domain.movie.model.MovieRecomendations
import io.reactivex.Single

interface MovieUseCase {

    fun getMovieByGenre(genreId : Int): Single<List<Movie>>

    fun getMovieRecomendations(movieId : Int): Single<List<MovieRecomendations>>

}