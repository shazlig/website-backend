package data.user

import data.user.db.UserDatabase
import data.user.model.UserItem
import data.user.model.request.InsertUserRequest
import data.user.model.request.ListUserRequest
import data.user.model.request.UpdateUserRequest
import io.reactivex.Flowable
import io.reactivex.Single

class UserDataStore(private val userDatabase: UserDatabase): UserRepository {

    override fun listUser(listUserRequest: ListUserRequest): Flowable<List<UserItem>> {
        return userDatabase.listUser(listUserRequest).map {
            it
        }
    }

    override fun getUser(userId: Int): Single<UserItem> {
        return userDatabase.getUser(userId).map {
            it
        }
    }

    override fun getUserByEmail(email: String): Single<Boolean> {
        return userDatabase.getUserByEmail(email).map {
            it
        }
    }

    override fun insertUser(insertUserRequest: InsertUserRequest): Single<Boolean> {
        return userDatabase.insertUser(insertUserRequest).map {
            it
        }
    }

    override fun updateUser(updateUserRequest: UpdateUserRequest): Single<Boolean> {
        return userDatabase.updateUser(updateUserRequest).map {
            it
        }
    }

    override fun deleteUser(userId: Int): Single<Boolean> {
        return userDatabase.deleteUser(userId).map {
            it
        }
    }
}