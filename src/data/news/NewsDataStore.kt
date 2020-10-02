package data.news

import data.news.db.*
import data.news.model.*
import data.news.model.request.*
import io.reactivex.*


class NewsDataStore(private val  newsDatabase: NewsDatabase):NewsRepository {

    override fun listNews(listNewsRequest: ListNewsRequest): Flowable<List<NewsItem>> {
        return newsDatabase.listNews(listNewsRequest).map {
            it
        }
    }

    override fun getNews(newsId: Int): Single<NewsItem> {
        return newsDatabase.getNews(newsId).map {
            it
        }
    }

    override fun insertNews(insertNewsRequest: InsertNewsRequest): Single<Boolean> {
        return newsDatabase.insertNews(insertNewsRequest)
    }

    override fun updateNews(updateNewsRequest: UpdateNewsRequest): Single<Boolean> {
        return newsDatabase.updateNews(updateNewsRequest)
    }

    override fun deleteNews(newsId: Int): Single<Boolean> {
        return newsDatabase.deleteNews(newsId)
    }


}
