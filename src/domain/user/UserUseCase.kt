package domain.user

import data.user.model.request.InsertUserRequest
import data.user.model.request.ListUserRequest
import data.user.model.request.UpdateUserRequest
import domain.user.model.User
import io.reactivex.Flowable
import io.reactivex.Single

interface UserUseCase {

    fun listUser(listUserRequest: ListUserRequest): Flowable<List<User>>

    fun getUser(userId: Int): Single<User>

    fun insertUser(insertUserRequest: InsertUserRequest): Single<Boolean>

    fun updateUser(updateUserRequest: UpdateUserRequest): Single<Boolean>

    fun deleteUser(userId: Int): Single<Boolean>

}