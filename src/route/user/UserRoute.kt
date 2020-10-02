package route.user

import com.google.gson.Gson
import data.user.model.request.InsertUserRequest
import data.user.model.request.ListUserRequest
import data.user.model.request.UpdateUserRequest
import domain.general.model.GeneralMessage
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.request.*
import io.ktor.response.*
import io.ktor.routing.*
import org.koin.ktor.ext.inject
import route.user.viewmodel.UserViewModel
import utils.isValidPassword

fun Route.userRoute(){

    val userViewModel : UserViewModel by inject()

    var errorMessage = GeneralMessage(statusCode = "", message = "")
    var resultMessage = GeneralMessage(statusCode = "", message = "")

    get("user/{id}") {
        try {
            val userId : Int = call.parameters["id"]?.toInt() ?: 0
            val result = userViewModel.getUser(userId)
            if (result.userId == 0) {
                call.respond(
                    HttpStatusCode.NotFound,
                    TextContent(
                        Gson().toJson(errorMessage.copy(statusCode = "404", message = "Data tidak ditemukan")),
                        ContentType.Application.Json)
                )
            }else{
                call.respond(HttpStatusCode.OK, userViewModel.getUser(userId))
            }
        }catch (e: Exception){
            errorMessage.copy(statusCode = "400", message = e.message.toString())
            call.respond(HttpStatusCode.InternalServerError, Gson().toJson(errorMessage))
        }
    }

    post("user/list"){
        val userRequest = call.receive<ListUserRequest>()
        try {
            if (userRequest.pageNumber.isNullOrEmpty()){
                return@post call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(errorMessage.copy(statusCode = "400", message = "Mandatory: pageNumber")),
                        ContentType.Application.Json)
                )
            }

            if (userRequest.pageSize.isNullOrEmpty()){
                return@post call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(errorMessage.copy(statusCode = "400", message = "Mandatory: pageSize")),
                        ContentType.Application.Json)
                )
            }
            val result = userViewModel.listUser(userRequest)
            if (result.isEmpty()) {
                call.respond(
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

    post("user"){
        val userRequest = call.receive<InsertUserRequest>()
        try {
            if (userRequest.username.isNullOrEmpty()){
                return@post call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory email")),
                        ContentType.Application.Json)
                )
            }

            if (userRequest.password.isNullOrEmpty()){
                return@post call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory password")),
                        ContentType.Application.Json)
                )
            }

            if (userRequest.userType.isNullOrEmpty()){
                return@post call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory userType")),
                        ContentType.Application.Json)
                )
            }

            if (userRequest.fullName.isNullOrEmpty()){
                return@post call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory fullName")),
                        ContentType.Application.Json)
                )
            }

            if (userRequest.userRekam.isNullOrEmpty()){
                return@post call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory userRekam")),
                        ContentType.Application.Json)
                )
            }

            if (userRequest.active.isNullOrEmpty()){
                return@post call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory active")),
                        ContentType.Application.Json)
                )
            }

            if(!isValidPassword(userRequest.password)){
                return@post call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Kata sandi harus terdapat kombinasi huruf kapital, angka, simbol, dan minimal 8 karakter")),
                        ContentType.Application.Json)
                )
            }

            if (userViewModel.insertUser(userRequest)){
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
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Gagal tambah user pastikan data benar dan email belum pernah digunakan")),
                        ContentType.Application.Json)
                )
            }
        }catch (e:Exception){
            errorMessage.copy(statusCode = "400", message = e.message.toString())
            call.respond(HttpStatusCode.BadRequest, Gson().toJson(errorMessage))
        }
    }

    put("user"){
        val userRequest = call.receive<UpdateUserRequest>()
        try {
            if (userRequest.userId.toString().isNullOrEmpty()){
                return@put call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory id")),
                        ContentType.Application.Json)
                )
            }

            if (userRequest.username.isNullOrEmpty()){
                return@put call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory email")),
                        ContentType.Application.Json)
                )
            }

            if (userRequest.password.isNullOrEmpty()){
                return@put call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory password")),
                        ContentType.Application.Json)
                )
            }

            if (userRequest.userType.isNullOrEmpty()){
                return@put call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory userType")),
                        ContentType.Application.Json)
                )
            }

            if (userRequest.fullName.isNullOrEmpty()){
                return@put call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory fullName")),
                        ContentType.Application.Json)
                )
            }

            if (userRequest.userUbah.isNullOrEmpty()){
                return@put call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory userUbah")),
                        ContentType.Application.Json)
                )
            }

            if (userRequest.active.isNullOrEmpty()){
                return@put call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Mandatory active")),
                        ContentType.Application.Json)
                )
            }


            if(!isValidPassword(userRequest.password)){
                return@put call.respond(
                    HttpStatusCode.BadRequest,
                    TextContent(
                        Gson().toJson(resultMessage.copy(statusCode = "400", message = "Kata sandi harus terdapat kombinasi huruf kapital, angka, simbol, dan minimal 8 karakter")),
                        ContentType.Application.Json)
                )
            }

            if (userViewModel.updateUser(userRequest)){
                return@put call.respond(
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

    delete("user/{id}") {
        try {
            val userId : Int = call.parameters["id"]?.toInt() ?: 0
            if (userViewModel.deleteUser(userId)){
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
