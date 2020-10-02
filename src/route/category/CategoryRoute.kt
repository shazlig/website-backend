package route.category

import com.google.gson.Gson
import data.category.model.request.InsertCategoryRequest
import data.category.model.request.ListCategoryRequest
import data.category.model.request.UpdateCategoryRequest
import domain.general.model.GeneralMessage
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject
import route.category.viewmodel.CategoryViewModel

fun Route.categoryRoute(){

    val categoryViewModel : CategoryViewModel by inject()

    var errorMessage = GeneralMessage(statusCode = "", message = "")
    var resultMessage = GeneralMessage(statusCode = "", message = "")

    get("category/{id}") {
        try {
            val categoryId : Int = call.parameters["id"]?.toInt() ?: 0
            val result = categoryViewModel.getCategory(categoryId)
            if (result.categoryId == 0) {
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

    post("category/list"){
        val categoryRequest = call.receive<ListCategoryRequest>()
        try {
            if (categoryRequest.pageNumber.isNullOrEmpty()){
                return@post call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(errorMessage.copy(statusCode = "400", message = "Mandatory: pageNumber")),
                        ContentType.Application.Json)
                )
            }

            if (categoryRequest.pageSize.isNullOrEmpty()){
                return@post call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(errorMessage.copy(statusCode = "400", message = "Mandatory: pageSize")),
                        ContentType.Application.Json)
                )
            }
            val result = categoryViewModel.listCategory(categoryRequest)
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

    post("category"){
        val categoryRequest = call.receive<InsertCategoryRequest>()
        try {
            if (categoryRequest.categoryType == ""){
                return@post call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory categoryType")),
                        ContentType.Application.Json)
                )
            }

            if (categoryRequest.categoryTitle == ""){
                return@post call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory categoryTitle")),
                        ContentType.Application.Json)
                )
            }

            if (categoryRequest.categoryShortDescription == ""){
                return@post call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory categoryShortDescription")),
                        ContentType.Application.Json)
                )
            }

            if (categoryRequest.categoryLongDescription == ""){
                return@post call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory categoryLongDescription")),
                        ContentType.Application.Json)
                )
            }

            if (categoryRequest.active == ""){
                return@post call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory active")),
                        ContentType.Application.Json)
                )
            }

            if (categoryRequest.langId == ""){
                return@post call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory langId")),
                        ContentType.Application.Json)
                )
            }

            if (categoryRequest.userRekam == ""){
                return@post call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory userRekam")),
                        ContentType.Application.Json)
                )
            }

            if (categoryViewModel.insertCategory(categoryRequest)){
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

    put("category"){
        val categoryRequest = call.receive<UpdateCategoryRequest>()
        try {

            if (categoryRequest.categoryId.toString().isNullOrEmpty()){
                return@put call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory categoryId")),
                        ContentType.Application.Json)
                )
            }

            if (categoryRequest.categoryType == ""){
                return@put call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory categoryType")),
                        ContentType.Application.Json)
                )
            }

            if (categoryRequest.categoryTitle == ""){
                return@put call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory categoryTitle")),
                        ContentType.Application.Json)
                )
            }

            if (categoryRequest.categoryShortDescription == ""){
                return@put call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory categoryShortDescription")),
                        ContentType.Application.Json)
                )
            }

            if (categoryRequest.categoryLongDescription == ""){
                return@put call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory categoryLongDescription")),
                        ContentType.Application.Json)
                )
            }

            if (categoryRequest.active == ""){
                return@put call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory active")),
                        ContentType.Application.Json)
                )
            }

            if (categoryRequest.langId == ""){
                return@put call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory langId")),
                        ContentType.Application.Json)
                )
            }

            if (categoryRequest.userUbah == ""){
                return@put call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory userUbah")),
                        ContentType.Application.Json)
                )
            }

            if (categoryViewModel.updateCategory(categoryRequest)){
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
            println(e)
            errorMessage.copy(statusCode = "400", message = e.message.toString())
            call.respond(HttpStatusCode.BadRequest, Gson().toJson(errorMessage))
        }
    }

    delete("category/{id}") {
        try {
            val categoryId : Int = call.parameters["id"]?.toInt() ?: 0
            if (categoryViewModel.deleteCategory(categoryId)){
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
