package di

import com.google.gson.GsonBuilder
import org.koin.dsl.module

val gsonModule = module{
    single { GsonBuilder().create() }
}