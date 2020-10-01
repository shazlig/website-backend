package data.user.model.request

import com.google.gson.annotations.SerializedName

data class UpdateUserRequest(

	@field:SerializedName("userUbah")
	val userUbah: String? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("email")
	val email: String? = null
)
