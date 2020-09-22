package domain.news

import data.news.NewsRepository
import data.news.model.request.ListNewsRequest
import data.news.model.request.InsertNewsRequest
import data.news.model.request.UpdateNewsRequest
import domain.news.model.News
import domain.news.model.toObject
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

class NewsInteractor(private val newsRepository: NewsRepository): NewsUseCase {

    override fun listNews(listNewsRequest: ListNewsRequest): Flowable<List<News>> {
        return newsRepository.listNews(listNewsRequest).map { list ->
            list.map { it.toObject() }
        }
    }

    override fun getNews(newsId: Int): Single<News> {
        return newsRepository.getNews(newsId).map {
            it.toObject()
        }
    }

    override fun insertNews(insertNewsRequest: InsertNewsRequest): Single<Boolean> {
        return newsRepository.insertNews(insertNewsRequest)
    }

    override fun updateNews(updateNewsRequest: UpdateNewsRequest): Single<Boolean> {
        return newsRepository.updateNews(updateNewsRequest)
    }

    override fun deleteNews(newsId: Int): Single<Boolean> {
        return newsRepository.deleteNews(newsId)
    }

}