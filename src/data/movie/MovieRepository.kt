package data.movie

import data.movie.model.response.MovieRecomendationsResponse
import data.movie.model.response.MovieResponse
import io.reactivex.Single
import retrofit2.Response

interface MovieRepository  {

    fun getMovieByGenre(genreId : Int): Single<Response<MovieResponse>>

    fun getMovieRecomendations(movieId : Int): Single<Response<MovieRecomendationsResponse>>

}