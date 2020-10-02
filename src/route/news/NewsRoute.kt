package route.news

import com.google.gson.Gson
import data.news.model.request.InsertNewsRequest
import data.news.model.request.ListNewsRequest
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
            if (result.newsId == 0) {
                call.respond(
                    HttpStatusCode.NotFound,
                    TextContent(
                        Gson().toJson(errorMessage.copy(statusCode = "404", message = "Data tidak ditemukan")),
                        ContentType.Application.Json)
                )
            }else{
                call.respond(HttpStatusCode.OK, result)
            }
        }catch (e: Exception){
            println(e)
            call.respond(HttpStatusCode.InternalServerError, Gson().toJson(errorMessage))
        }
    }

    post("news/list"){
        val newsRequest = call.receive<ListNewsRequest>()
        try {
            if (newsRequest.pageNumber.isNullOrEmpty()){
                return@post call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(errorMessage.copy(statusCode = "400", message = "Mandatory: pageNumber")),
                        ContentType.Application.Json)
                )
            }

            if (newsRequest.pageSize.isNullOrEmpty()){
                return@post call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(errorMessage.copy(statusCode = "400", message = "Mandatory: pageSize")),
                        ContentType.Application.Json)
                )
            }
            val result = newsViewModel.listNews(newsRequest)
            if (result.isEmpty()) {
                return@post call.respond(
                    HttpStatusCode.NotFound,
                    TextContent(
                        Gson().toJson(errorMessage.copy(statusCode = "404", message = "Data tidak ditemukan")),
                        ContentType.Application.Json)
                )
            }else{
                call.respond(HttpStatusCode.OK, result)
            }
        }catch (e:Exception){
            println(e)
            errorMessage.copy(statusCode = "400", message = e.message.toString())
            call.respond(HttpStatusCode.BadRequest, Gson().toJson(errorMessage))
        }
    }

    post("news"){
        val newsRequest = call.receive<InsertNewsRequest>()
        try {
            if (newsRequest.newsCategoryId == ""){
                return@post call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory newsCategoryId")),
                        ContentType.Application.Json)
                )
            }

            if (newsRequest.newsTitle == ""){
                return@post call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory newsTitle")),
                        ContentType.Application.Json)
                )
            }

            if (newsRequest.newsMetaData == ""){
                return@post call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory newsMetaData")),
                        ContentType.Application.Json)
                )
            }

            if (newsRequest.newsShortContent == ""){
                return@post call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory newsShortContent")),
                        ContentType.Application.Json)
                )
            }

            if (newsRequest.newsLongContent == ""){
                return@post call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory newsLongContent")),
                        ContentType.Application.Json)
                )
            }

            if (newsRequest.newsAuthor == ""){
                return@post call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory newsAuthor")),
                        ContentType.Application.Json)
                )
            }

            if (newsRequest.langId == ""){
                return@post call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory langId")),
                        ContentType.Application.Json)
                )
            }

            if (newsRequest.active == ""){
                return@post call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory status")),
                        ContentType.Application.Json)
                )
            }

            if (newsRequest.userRekam == ""){
                return@post call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory userUbah")),
                        ContentType.Application.Json)
                )
            }

            if (newsRequest.imagePathUrl == ""){
                return@post call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory imagePathUrl")),
                        ContentType.Application.Json)
                )
            }

            if (newsViewModel.insertNews(newsRequest)){
                return@post call.respond(
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
            if (newsRequest.newsId.toString() == ""){
                return@put call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory newsId")),
                        ContentType.Application.Json)
                )
            }

            if (newsRequest.newsCategoryId == ""){
                return@put call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory newsCategoryId")),
                        ContentType.Application.Json)
                )
            }

            if (newsRequest.newsTitle == ""){
                return@put call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory newsTitle")),
                        ContentType.Application.Json)
                )
            }

            if (newsRequest.newsMetaData == ""){
                return@put call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory newsMetaData")),
                        ContentType.Application.Json)
                )
            }

            if (newsRequest.newsShortContent == ""){
                return@put call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory newsShortContent")),
                        ContentType.Application.Json)
                )
            }

            if (newsRequest.newsLongContent == ""){
                return@put call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory newsLongContent")),
                        ContentType.Application.Json)
                )
            }

            if (newsRequest.newsAuthor == ""){
                return@put call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory newsAuthor")),
                        ContentType.Application.Json)
                )
            }

            if (newsRequest.langId == ""){
                return@put call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory langId")),
                        ContentType.Application.Json)
                )
            }

            if (newsRequest.active == ""){
                return@put call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory status")),
                        ContentType.Application.Json)
                )
            }

            if (newsRequest.userUbah == ""){
                return@put call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory userUbah")),
                        ContentType.Application.Json)
                )
            }

            if (newsRequest.imagePathUrl == ""){
                return@put call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory imagePathUrl")),
                        ContentType.Application.Json)
                )
            }

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
