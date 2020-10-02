package data.category.model.request

import com.google.gson.annotations.SerializedName

data class UpdateCategoryRequest(

    @field:SerializedName("categoryId")
    val categoryId: Int? = null,

    @field:SerializedName("categoryType")
    val categoryType: String? = null,

    @field:SerializedName("userUbah")
    val userUbah: String? = null,

    @field:SerializedName("categoryTitle")
    val categoryTitle: String? = null,

    @field:SerializedName("categoryLongDescription")
    val categoryLongDescription: String? = null,

    @field:SerializedName("active")
    val active: String? = null,

    @field:SerializedName("categoryShortDescription")
    val categoryShortDescription: String? = null,

    @field:SerializedName("langId")
    val langId: String? = null
)
