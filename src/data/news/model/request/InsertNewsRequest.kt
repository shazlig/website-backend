package data.news.model.request

import com.google.gson.annotations.SerializedName

class InsertNewsRequest {

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

    @SerializedName("userRekam")
    val userRekam: String? = null

}