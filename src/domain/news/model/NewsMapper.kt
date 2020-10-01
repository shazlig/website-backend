package domain.news.model

import data.news.model.NewsItem

fun NewsItem.toObject(): News {
    return News(
            id=id,
            title= title,
            keywords= keywords,
            descriptionContent= descriptionContent,
            content= content,
            imageUrl= imageUrl,
            slug= slug,
            createdAt= createdAt
    )
}