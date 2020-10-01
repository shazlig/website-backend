package domain.user

import data.user.UserRepository
import data.user.model.request.InsertUserRequest
import data.user.model.request.ListUserRequest
import data.user.model.request.UpdateUserRequest
import domain.user.model.User
import domain.user.model.toObject
import io.reactivex.Flowable
import io.reactivex.Single

class UserInteractor(private val userRepository: UserRepository):UserUseCase {

    override fun listUser(listUserRequest: ListUserRequest): Flowable<List<User>> {
        return userRepository.listUser(listUserRequest).map { list ->
            list.map { it.toObject() }
        }
    }

    override fun getUser(userId: Int): Single<User> {
        return userRepository.getUser(userId).map {
            it.toObject()
        }
    }

    override fun insertUser(insertUserRequest: InsertUserRequest): Single<Boolean> {
        return userRepository.insertUser(insertUserRequest)
    }

    override fun updateUser(updateUserRequest: UpdateUserRequest): Single<Boolean> {
        return userRepository.updateUser(updateUserRequest)
    }

    override fun deleteUser(userId: Int): Single<Boolean> {
        return userRepository.deleteUser(userId)
    }
}