package route.user.viewmodel

import data.user.model.request.InsertUserRequest
import data.user.model.request.ListUserRequest
import data.user.model.request.UpdateUserRequest
import domain.user.UserUseCase
import domain.user.model.User
import io.reactivex.disposables.CompositeDisposable
import utils.rx.ext.addTo

class UserViewModelImpl(
    private val userUseCase: UserUseCase,
    private val compositeDisposable: CompositeDisposable
): UserViewModel {

    override fun listUser(listUserRequest: ListUserRequest): List<User> {
        var result: List<User> = arrayListOf()
        userUseCase.listUser(listUserRequest)
            .subscribe({
                result = it
            },{
                println(it)
            }).addTo(compositeDisposable)
        return result
    }

    override fun getUser(userId: Int): User {
        var result = User()
        userUseCase.getUser(userId)
            .subscribe({
                result = it
            },{
                println(it)
            }).addTo(compositeDisposable)
        return result
    }

    override fun insertUser(insertUserRequest: InsertUserRequest): Boolean {
        var result  = false
        userUseCase.insertUser(insertUserRequest)
            .subscribe({
                result = it
            }, {
                println(it)
            })
        return result
    }

    override fun updateUser(updateUserRequest: UpdateUserRequest): Boolean {
        var result  = false
        userUseCase.updateUser(updateUserRequest)
            .subscribe({
                result = it
            }, {
                println(it)
            })
        return result
    }

    override fun deleteUser(userId: Int): Boolean {
        var result  = false
        userUseCase.deleteUser(userId)
            .subscribe({
                result = it
            }, {
                println(it)
            })
        return result
    }
}