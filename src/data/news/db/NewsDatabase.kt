package data.news.db

import data.news.model.NewsItem
import data.news.model.request.ListNewsRequest
import data.news.model.request.InsertNewsRequest
import data.news.model.request.UpdateNewsRequest
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

interface NewsDatabase {

    fun listNews(listNewsRequest: ListNewsRequest): Flowable<List<NewsItem>>

    fun getNews(newsId: Int): Single<NewsItem>

    fun insertNews(insertNewsRequest: InsertNewsRequest): Single<Boolean>

    fun updateNews(updateNewsRequest: UpdateNewsRequest): Single<Boolean>

    fun deleteNews(newsId: Int): Single<Boolean>

}