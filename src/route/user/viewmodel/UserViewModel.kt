package route.user.viewmodel

import data.user.model.request.InsertUserRequest
import data.user.model.request.ListUserRequest
import data.user.model.request.UpdateUserRequest
import domain.user.model.User
import io.reactivex.Flowable
import io.reactivex.Single

interface UserViewModel {

    fun listUser(listUserRequest: ListUserRequest): List<User>

    fun getUser(userId: Int): User

    fun insertUser(insertUserRequest: InsertUserRequest): Boolean

    fun updateUser(updateUserRequest: UpdateUserRequest): Boolean

    fun deleteUser(userId: Int): Boolean

}