package base

import di.dbModule
import di.features.newsModule
import di.gsonModule
import di.rxModule
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.koin.core.context.startKoin
import org.koin.logger.SLF4JLogger
import route.home.homeRoute
import route.news.newsRoute

fun main(args: Array<String>) {
    embeddedServer(Netty, commandLineEnvironment(args)).start()
}

fun Application.main() {
    install(DefaultHeaders){
        header("X-Application-Id", "Website Portal BP Jamsostek")
    }
    install(CallLogging)
    install(Compression)
    install(ContentNegotiation) {
        gson {
            setPrettyPrinting()
        }
    }


    SLF4JLogger()
    startKoin {
        modules(
            listOf(dbModule, gsonModule, rxModule, newsModule)
        )
    }

    routing {
        homeRoute()
        newsRoute()
    }
}
