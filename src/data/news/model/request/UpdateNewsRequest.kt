package data.news.model.request

import com.google.gson.annotations.SerializedName

class UpdateNewsRequest {

    @SerializedName("id")
    val id: Int = 0

    @SerializedName("title")
    val title: String? = null

    @SerializedName("keywords")
    val keywords: String? = null

    @SerializedName("descriptionContent")
    val descriptionContent: String? = null

    @SerializedName("content")
    val content: String? = null

    @SerializedName("imageUrl")
    val imageUrl: String? = null

    @SerializedName("userUbah")
    val userUbah: String? = null

}