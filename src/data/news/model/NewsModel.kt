package data.news.model

data class NewsItem(
    var id: Int = 0,
    var title: String = "",
    var keywords: String= "",
    var descriptionContent: String= "",
    var content: String= "",
    var imageUrl: String= "",
    var slug: String= "",
    var createdAt: String= "",
    var updatedAt: String= "",
    var userRekam: String= "",
    var userUbah: String= ""
)