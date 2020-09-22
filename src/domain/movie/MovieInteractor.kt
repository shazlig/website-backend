package domain.movie

import data.movie.MovieRepository
import domain.movie.model.Movie
import domain.movie.model.MovieRecomendations
import domain.movie.model.toObject
import io.reactivex.Single


class MovieInteractor(private val repository: MovieRepository): MovieUseCase {

    override fun getMovieByGenre(genreId: Int): Single<List<Movie>> {
       return repository.getMovieByGenre(genreId).map {list ->
           list.body()?.results?.map {
               it.toObject()
           }
       }
    }

    override fun getMovieRecomendations(movieId: Int): Single<List<MovieRecomendations>> {
        return repository.getMovieRecomendations(movieId).map {list ->
            list.body()?.results?.map {
                it.toObject()
            }
        }
    }

}