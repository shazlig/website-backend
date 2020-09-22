package domain.news.model

import data.news.model.NewsItem

fun NewsItem.toObject(): News {
    return News(
            id=id,
            title=title.orEmpty(),
            keywords=keywords.orEmpty(),
            descriptionContent=descriptionContent.orEmpty(),
            content=content.orEmpty(),
            imageUrl=imageUrl.orEmpty(),
        slug=slug.orEmpty(),
            createdAt=createdAt.orEmpty()
    )
}