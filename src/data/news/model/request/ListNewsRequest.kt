package data.news.model.request

import com.google.gson.annotations.SerializedName

data class ListNewsRequest(

	@field:SerializedName("pageNumber")
	val pageNumber: String? = null,

	@field:SerializedName("searchBy")
	val searchBy: String? = null,

	@field:SerializedName("pageSize")
	val pageSize: String? = null,

	@field:SerializedName("searchValue")
	val searchValue: String? = null,

	@field:SerializedName("langId")
	val langId: String? = null
)
