package route.home

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.response.*
import io.ktor.routing.*

fun Route.homeRoute(){
    get("/") {
        call.respond(HttpStatusCode.OK, "Hallo")
    }
}