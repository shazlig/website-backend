package domain.news

import data.news.model.NewsItem
import data.news.model.request.ListNewsRequest
import data.news.model.request.InsertNewsRequest
import data.news.model.request.UpdateNewsRequest
import domain.news.model.News
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

interface NewsUseCase {

    fun listNews(listNewsRequest: ListNewsRequest): Flowable<List<News>>

    fun getNews(newsId:Int): Single<News>

    fun insertNews(insertNewsRequest: InsertNewsRequest): Single<Boolean>

    fun updateNews(updateNewsRequest: UpdateNewsRequest): Single<Boolean>

    fun deleteNews(newsId: Int): Single<Boolean>

}