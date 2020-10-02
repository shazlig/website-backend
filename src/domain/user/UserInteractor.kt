package domain.user

import data.user.UserRepository
import data.user.model.request.InsertUserRequest
import data.user.model.request.ListUserRequest
import data.user.model.request.UpdateUserRequest
import domain.user.model.User
import domain.user.model.toObject
import io.reactivex.Flowable
import io.reactivex.Single
import utils.generateHashKey

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
        val email : String = insertUserRequest.username.toString()

        return userRepository.getUserByEmail(email)
            .flatMap {
                when (it){
                    false -> saveUser(insertUserRequest)
                    else -> Single.just(false)
                }
            }
            .map {
                it
            }
    }

    private fun saveUser(insertUserRequest: InsertUserRequest):Single<Boolean> {
        val encryptedPassword = InsertUserRequest(
            username = insertUserRequest.username,
            password = insertUserRequest.password?.let { generateHashKey(it) },
            userType = insertUserRequest.userType,
            fullName = insertUserRequest.fullName,
            userRekam = insertUserRequest.userRekam,
            active = insertUserRequest.active
        )
        return userRepository.insertUser(encryptedPassword)
    }

    override fun updateUser(updateUserRequest: UpdateUserRequest): Single<Boolean> {
        val encryptedPassword = UpdateUserRequest(
            userId = updateUserRequest.userId,
            username = updateUserRequest.username,
            password = updateUserRequest.password?.let { generateHashKey(it) },
            userType = updateUserRequest.userType,
            fullName = updateUserRequest.fullName,
            userUbah = updateUserRequest.userUbah,
            active = updateUserRequest.active
        )
        return userRepository.updateUser(encryptedPassword)
    }

    override fun deleteUser(userId: Int): Single<Boolean> {
        return userRepository.deleteUser(userId)
    }
}