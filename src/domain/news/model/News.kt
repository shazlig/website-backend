package domain.news.model

data class News(
    val id: Int = 0,
    val title: String = "",
    val keywords: String= "",
    val descriptionContent: String= "",
    val content: String= "",
    val imageUrl: String= "",
    val slug: String= "",
    val createdAt: String= ""
)