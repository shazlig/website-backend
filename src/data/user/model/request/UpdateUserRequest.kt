package data.user.model.request

import com.google.gson.annotations.SerializedName

data class UpdateUserRequest(

	@field:SerializedName("userId")
	val userId: Int,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("userUbah")
	val userUbah: String? = null,

	@field:SerializedName("fullName")
	val fullName: String? = null,

	@field:SerializedName("active")
	val active: String? = null,

	@field:SerializedName("userType")
	val userType: String? = null,

	@field:SerializedName("username")
	val username: String? = null
)
