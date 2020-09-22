package data.movie.remote

import data.movie.model.response.MovieRecomendationsResponse
import data.movie.model.response.MovieResponse
import io.reactivex.Single
import retrofit2.Response


class MovieApi(private val movieApiClient: MovieApiClient) : MovieApiClient {

    override fun getMovieByGenre(genreId: Int): Single<Response<MovieResponse>> {
        return movieApiClient.getMovieByGenre(genreId)
    }

    override fun getMovieRecomendations(movieId: Int): Single<Response<MovieRecomendationsResponse>> {
        return movieApiClient.getMovieRecomendations(movieId)
    }

}