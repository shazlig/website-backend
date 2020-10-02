package data.category.model

data class CategoryItem(
    var categoryId: Int? = 0,
    var categoryType: String? = null,
    var categoryTitle: String? = null,
    var categoryShortDescription: String? = null,
    var categoryLongDescription: String? = null,
    var active: String? = null,
    var slug: String? = null,
    var langId: String? = null,
    var createdAt: String? = null
)

