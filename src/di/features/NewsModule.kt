package di.features

import data.news.NewsDataStore
import data.news.NewsRepository
import data.news.db.NewsDatabase
import data.news.db.NewsDatabaseImpl
import domain.news.NewsInteractor
import domain.news.NewsUseCase
import org.koin.core.qualifier.StringQualifier
import org.koin.core.qualifier.named
import org.koin.dsl.module
import route.news.viewmodel.NewsViewModel
import route.news.viewmodel.NewsViewModelImpl

val newsModule = module {

    single<NewsDatabase> { NewsDatabaseImpl(get(named("mysql"))) }

    single<NewsRepository> { NewsDataStore(get()) }

    single<NewsUseCase> { NewsInteractor(get()) }

    single<NewsViewModel> { NewsViewModelImpl(get(), get()) }

}