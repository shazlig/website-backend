package route.news.viewmodel

import data.news.model.request.InsertNewsRequest
import data.news.model.request.ListNewsRequest
import data.news.model.request.UpdateNewsRequest
import domain.news.NewsUseCase
import domain.news.model.News
import io.reactivex.disposables.CompositeDisposable
import utils.rx.ext.addTo

class NewsViewModelImpl(
    private val newsUseCase: NewsUseCase,
    private val compositeDisposable: CompositeDisposable
):NewsViewModel {

    override fun listNews(listNewsRequest: ListNewsRequest): List<News> {

        var result: List<News> = arrayListOf()
        newsUseCase.listNews(listNewsRequest)
            .subscribe({
                result = it
            },{
                println(it)
            }).addTo(compositeDisposable)
        return result
    }

    override fun getNews(newsId: Int): News {
        var result = News()
        newsUseCase.getNews(newsId)
            .subscribe({
                result = it
            },{
                println(it)
            }).addTo(compositeDisposable)
        return result
    }

    override fun insertNews(insertNewsRequest: InsertNewsRequest): Boolean {
        var result  = false
        newsUseCase.insertNews(insertNewsRequest)
            .subscribe({
                result = it
            }, {
                println(it)
            }).addTo(compositeDisposable)
        return result
    }

    override fun updateNews(updateNewsRequest: UpdateNewsRequest): Boolean {
        var result  = false
        newsUseCase.updateNews(updateNewsRequest)
            .subscribe({
                result = it
            }, {
                println(it)
            }).addTo(compositeDisposable)
        return result
    }

    override fun deleteNews(newsId: Int): Boolean {
        var result  = false
        newsUseCase.deleteNews(newsId)
            .subscribe({
                result = it
            }, {
                println(it)
            }).addTo(compositeDisposable)
        return result
    }


}