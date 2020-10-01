package data.user.model.request

import com.google.gson.annotations.SerializedName

data class InsertUserRequest(

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("userRekam")
	val userRekam: String? = null,

	@field:SerializedName("email")
	val email: String? = null
)
