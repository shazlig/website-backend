package base

import di.apiModule
import di.dbModule
import di.features.movieModule
import di.features.newsModule
import di.gsonModule
import di.rxModule
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.gson.*
import io.ktor.http.*
import io.ktor.routing.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.sessions.*
import org.koin.core.context.startKoin
import org.koin.logger.SLF4JLogger
import route.movie.movieRoute
import route.news.newsRoute

fun main(args: Array<String>) {
    embeddedServer(Netty, commandLineEnvironment(args)).start()
}

fun Application.main() {
    install(DefaultHeaders){
        header("X-Developer", "BP Jamsostek")
        header(HttpHeaders.Server, "..")
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
            listOf(apiModule, gsonModule, rxModule, movieModule)
        )
    }

    routing {
        newsRoute()
        movieRoute()
    }
}
