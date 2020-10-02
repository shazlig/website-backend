package domain.news.model

data class News(
    var newsLongContent: String? = null,
    var createdAt: String? = null,
    var newsId: Int? = 0,
    var newsAuthor: String? = null,
    var newsTitle: String? = null,
    var userRekam: String? = null,
    var newsMetaData: String? = null,
    var newsCategoryId: Int? = 0,
    var langId: String? = null,
    var newsShortContent: String? = null,
    var slug: String? = null,
    var active: String? = null,
    var imagePathUrl: String? = null
)