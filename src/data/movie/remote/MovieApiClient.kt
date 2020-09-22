package data.movie.remote

import data.movie.model.response.MovieRecomendationsResponse
import data.movie.model.response.MovieResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface MovieApiClient {

    @GET("discover/movie?api_key=7012fc3c96c9f6f707e4edb9c9725718")
    fun getMovieByGenre(@Query("with_genres")genreId : Int): Single<Response<MovieResponse>>

    @GET("movie/{movieId}/recommendations?api_key=7012fc3c96c9f6f707e4edb9c9725718")
    fun getMovieRecomendations(@Path("movieId")movieId : Int): Single<Response<MovieRecomendationsResponse>>

}