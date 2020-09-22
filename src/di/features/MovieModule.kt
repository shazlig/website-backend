package di.features

import data.movie.MovieDataStore
import data.libs.remote.ApiService
import data.movie.MovieRepository
import data.movie.remote.MovieApi
import data.movie.remote.MovieApiClient
import di.BASE_URL
import domain.movie.MovieInteractor
import domain.movie.MovieUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module
import route.movie.viewmodel.MovieViewModel
import route.movie.viewmodel.MovieViewModelImpl



val movieModule = module {

    single { ApiService.createReactiveService(MovieApiClient::class.java, get(), get(named(BASE_URL))) }

    single { MovieApi(get()) }

    single<MovieRepository> { MovieDataStore(get()) }

    single<MovieUseCase> { MovieInteractor(get()) }

    single<MovieViewModel> { MovieViewModelImpl(get(), get()) }
}