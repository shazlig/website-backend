package di.features

import data.user.UserDataStore
import data.user.UserRepository
import data.user.db.UserDatabase
import data.user.db.UserDatabaseImpl
import domain.user.UserInteractor
import domain.user.UserUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module
import route.user.viewmodel.UserViewModel
import route.user.viewmodel.UserViewModelImpl

val userModule = module {

    single<UserDatabase> { UserDatabaseImpl(get(named("mysql"))) }

    single<UserRepository> { UserDataStore(get()) }

    single<UserUseCase> { UserInteractor(get()) }

    single<UserViewModel> { UserViewModelImpl(get(), get()) }

}