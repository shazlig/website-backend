package route.news

import com.google.gson.Gson
import data.news.model.request.ListNewsRequest
import data.news.model.request.InsertNewsRequest
import data.news.model.request.UpdateNewsRequest
import domain.general.model.GeneralMessage
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject
import route.news.viewmodel.NewsViewModel


fun Route.newsRoute(){

    val newsViewModel : NewsViewModel by inject()

    var errorMessage = GeneralMessage(statusCode = "", message = "")
    var resultMessage = GeneralMessage(statusCode = "", message = "")

    get("news/{id}") {
        try {
            val newsId : Int = call.parameters["id"]?.toInt() ?: 0
            val result = newsViewModel.getNews(newsId)
            if (result.id == 0) {
                call.respond(
                    HttpStatusCode.NotFound,
                    TextContent(
                        Gson().toJson(errorMessage.copy(statusCode = "404", message = "Data tidak ditemukan")),
                        ContentType.Application.Json)
                )
            }
            call.respond(HttpStatusCode.OK, newsViewModel.getNews(newsId))
        }catch (e: Exception){
            errorMessage.copy(statusCode = "400", message = e.message.toString())
            call.respond(HttpStatusCode.InternalServerError, Gson().toJson(errorMessage))
        }
    }

    post("news/list"){
        val newsRequest = call.receive<ListNewsRequest>()
        if (newsRequest.pageNumber.isNullOrEmpty()){
            call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                            Gson().toJson(errorMessage.copy(statusCode = "400", message = "Mandatory: pageNumber")),
                            ContentType.Application.Json)
            )
        }

        if (newsRequest.pageSize.isNullOrEmpty()){
            call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                            Gson().toJson(errorMessage.copy(statusCode = "400", message = "Mandatory: pageSize")),
                            ContentType.Application.Json)
            )
        }



        try {
            call.respond(HttpStatusCode.OK, newsViewModel.listNews(newsRequest))
        }catch (e:Exception){
            println(e)
            errorMessage.copy(statusCode = "400", message = e.message.toString())
            call.respond(HttpStatusCode.BadRequest, Gson().toJson(errorMessage))
        }
    }

    post("news"){
        val newsRequest = call.receive<InsertNewsRequest>()
        try {
            if (newsViewModel.insertNews(newsRequest)){
                call.respond(
                    HttpStatusCode.OK,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "200", message = "Success")),
                        ContentType.Application.Json)
                )
            }else{
                call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Failed to insert data")),
                        ContentType.Application.Json)
                )
            }
        }catch (e:Exception){
            errorMessage.copy(statusCode = "400", message = e.message.toString())
            call.respond(HttpStatusCode.BadRequest, Gson().toJson(errorMessage))
        }
    }

    put("news"){
        val newsRequest = call.receive<UpdateNewsRequest>()
        try {
            if (newsViewModel.updateNews(newsRequest)){
                call.respond(
                    HttpStatusCode.OK,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "200", message = "Success")),
                        ContentType.Application.Json)
                )
            }else{
                call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Failed to update data")),
                        ContentType.Application.Json)
                )
            }
        }catch (e:Exception){
            errorMessage.copy(statusCode = "400", message = e.message.toString())
            call.respond(HttpStatusCode.BadRequest, Gson().toJson(errorMessage))
        }
    }

    delete("news/{id}") {
        try {
            val newsId : Int = call.parameters["id"]?.toInt() ?: 0
            if (newsViewModel.deleteNews(newsId)){
                call.respond(
                    HttpStatusCode.OK,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "200", message = "Success")),
                        ContentType.Application.Json)
                )
            }else{
                call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Failed to delete data")),
                        ContentType.Application.Json)
                )
            }
        }catch (e: Exception){
            errorMessage.copy(statusCode = "400", message = e.message.toString())
            call.respond(HttpStatusCode.InternalServerError, Gson().toJson(errorMessage))
        }
    }
}
