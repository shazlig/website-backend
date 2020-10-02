package data.user

import data.user.model.UserItem
import data.user.model.request.InsertUserRequest
import data.user.model.request.ListUserRequest
import data.user.model.request.UpdateUserRequest
import io.reactivex.Flowable
import io.reactivex.Single

interface UserRepository {
    fun listUser(listUserRequest: ListUserRequest): Flowable<List<UserItem>>

    fun getUser(userId: Int): Single<UserItem>

    fun getUserByEmail(email: String): Single<Boolean>

    fun insertUser(insertUserRequest: InsertUserRequest): Single<Boolean>

    fun updateUser(updateUserRequest: UpdateUserRequest): Single<Boolean>

    fun deleteUser(userId: Int): Single<Boolean>
}