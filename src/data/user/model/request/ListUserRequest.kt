package data.user.model.request

import com.google.gson.annotations.SerializedName

data class ListUserRequest(

	@field:SerializedName("pageNumber")
	val pageNumber: String? = null,

	@field:SerializedName("searchBy")
	val searchBy: String? = null,

	@field:SerializedName("pageSize")
	val pageSize: String? = null,

	@field:SerializedName("searchValue")
	val searchValue: String? = null
)
