package route.home

import com.google.gson.Gson
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.homeRoute(){
    get("/") {
        call.respond(HttpStatusCode.OK, "Hallo")
    }
}