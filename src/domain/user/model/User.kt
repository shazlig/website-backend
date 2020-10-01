package domain.user.model

data class User(
    var id: Int = 0,
    var email: String = "",
    var password: String= "",
    var createdAt: String= ""
)