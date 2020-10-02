package di.features

import data.category.CategoryDataStore
import data.category.CategoryRepository
import data.category.db.CategoryDatabase
import data.category.db.CategoryDatabaseImpl
import domain.category.CategoryInteractor
import domain.category.CategoryUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module
import route.category.viewmodel.CategoryViewModel
import route.category.viewmodel.CategoryViewModelImpl

val categoryModule = module {

    single<CategoryDatabase> { CategoryDatabaseImpl(get(named("mysql"))) }

    single<CategoryRepository> { CategoryDataStore(get()) }

    single<CategoryUseCase> { CategoryInteractor(get()) }

    single<CategoryViewModel> { CategoryViewModelImpl(get(), get()) }

}