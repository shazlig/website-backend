package route.movie

import com.google.gson.Gson
import domain.general.model.GeneralMessage
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject
import route.movie.viewmodel.MovieViewModel

fun Route.movieRoute() {

    val movieViewModel: MovieViewModel by inject()

    var errorMessage = GeneralMessage(statusCode = "", message = "")
    var resultMessage = GeneralMessage(statusCode = "", message = "")

    get("movie") {
        try {
            val result = movieViewModel.listMovie()
            println(result)
            call.respond(HttpStatusCode.OK, result)
        } catch (e: Exception) {
            println(e)
            errorMessage.copy(statusCode = "400", message = e.message.toString())
            call.respond(HttpStatusCode.InternalServerError, Gson().toJson(errorMessage))
        }
    }
}