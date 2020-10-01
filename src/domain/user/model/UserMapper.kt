package domain.user.model

import data.user.model.UserItem

fun UserItem.toObject(): User {
    return User(
        id = id,
        email = email,
        password = password,
        createdAt = createdAt
    )
}