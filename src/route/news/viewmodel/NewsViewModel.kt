package route.news.viewmodel

import data.news.model.request.InsertNewsRequest
import data.news.model.request.ListNewsRequest
import data.news.model.request.UpdateNewsRequest
import domain.news.model.News

interface NewsViewModel{

    fun listNews(listNewsRequest: ListNewsRequest): List<News>

    fun getNews(newsId: Int): News

    fun insertNews(insertNewsRequest: InsertNewsRequest): Boolean

    fun updateNews(updateNewsRequest: UpdateNewsRequest): Boolean

    fun deleteNews(newsId: Int): Boolean

}