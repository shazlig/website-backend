package domain.user.model

import data.user.model.UserItem

fun UserItem.toObject(): User {
    return User(
        userId = userId,
        username = username.orEmpty(),
        password = password.orEmpty(),
        fullName = fullName.orEmpty(),
        userType = userType.orEmpty(),
        active = active.orEmpty(),
        createdAt = createdAt.orEmpty()
    )
}