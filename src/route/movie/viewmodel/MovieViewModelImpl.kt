package route.movie.viewmodel

import domain.movie.MovieUseCase
import domain.movie.model.Movie
import io.reactivex.disposables.CompositeDisposable
import utils.rx.ext.addTo

class MovieViewModelImpl(
    private val movieUseCase: MovieUseCase,
    private val compositeDisposable: CompositeDisposable
): MovieViewModel {

    override fun listMovie(): List<Movie> {
        var result: List<Movie> = arrayListOf()
        movieUseCase.getMovieByGenre(16)
            .subscribe({
                println(it)
                result = it
            }, {
                println(it)
            }).addTo(compositeDisposable)
        return result
    }
}