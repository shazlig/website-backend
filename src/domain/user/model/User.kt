package domain.user.model

data class User(
    var createdAt: String? = null,
    var password: String? = null,
    var userRekam: String? = null,
    var fullName: String? = null,
    var active: String? = null,
    var userType: String? = null,
    var userId: Int? = 0,
    var username: String? = null
)