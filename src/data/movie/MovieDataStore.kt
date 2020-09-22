package data.movie

import data.movie.model.response.MovieRecomendationsResponse
import data.movie.model.response.MovieResponse
import data.movie.remote.MovieApi
import io.reactivex.Single
import retrofit2.Response


class MovieDataStore(private val movieApi: MovieApi) : MovieRepository {


    override fun getMovieByGenre(genreId: Int): Single<Response<MovieResponse>> {
        return movieApi.getMovieByGenre(genreId)
            .map {
                it
            }
    }

    override fun getMovieRecomendations(movieId: Int): Single<Response<MovieRecomendationsResponse>> {
        return movieApi.getMovieRecomendations(movieId).map { it }
    }

}