package domain.news.model

import data.news.model.NewsItem

fun NewsItem.toObject(): News {
    return News(
            newsId = newsId,
            newsCategoryId = newsCategoryId,
            newsTitle= newsTitle.orEmpty(),
            newsMetaData = newsMetaData.orEmpty(),
            newsShortContent = newsShortContent.orEmpty(),
            newsLongContent = newsLongContent.orEmpty(),
            newsAuthor = newsAuthor.orEmpty(),
            imagePathUrl = imagePathUrl.orEmpty(),
            slug = slug.orEmpty(),
            langId = langId.orEmpty(),
            active = active.orEmpty(),
            createdAt = createdAt.orEmpty(),
            userRekam = userRekam.orEmpty()
    )
}