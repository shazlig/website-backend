package data.news.model.request

import com.google.gson.annotations.SerializedName

data class UpdateNewsRequest(

	@field:SerializedName("newsId")
	val newsId: Int,

	@field:SerializedName("newsLongContent")
	val newsLongContent: String? = null,

	@field:SerializedName("newsAuthor")
	val newsAuthor: String? = null,

	@field:SerializedName("newsTitle")
	val newsTitle: String? = null,

	@field:SerializedName("userUbah")
	val userUbah: String? = null,

	@field:SerializedName("newsMetaData")
	val newsMetaData: String? = null,

	@field:SerializedName("newsCategoryId")
	val newsCategoryId: String? = null,

	@field:SerializedName("langId")
	val langId: String? = null,

	@field:SerializedName("newsShortContent")
	val newsShortContent: String? = null,

	@field:SerializedName("active")
	val active: String? = null,

	@field:SerializedName("imagePathUrl")
	val imagePathUrl: String? = null
)
