package data.news.db

import com.zaxxer.hikari.HikariDataSource
import data.news.model.NewsItem
import data.news.model.request.InsertNewsRequest
import data.news.model.request.ListNewsRequest
import data.news.model.request.UpdateNewsRequest
import io.reactivex.Flowable
import io.reactivex.Single
import utils.toSlug
import java.sql.Connection
import java.sql.PreparedStatement


class NewsDatabaseImpl(private val dataSource: HikariDataSource): NewsDatabase {


    override fun listNews(listNewsRequest: ListNewsRequest): Flowable<List<NewsItem>> {
        val connection : Connection = dataSource.connection
        val result: MutableList<NewsItem> = arrayListOf()
        val pageNumber: Int = if (listNewsRequest.pageNumber.equals("1")) {
            0
        } else {
            listNewsRequest.pageNumber?.toInt()?.minus(1) ?: 0
        }

        val sql: String
        val preparedStatement: PreparedStatement

        if (!listNewsRequest.searchBy.isNullOrEmpty() && !listNewsRequest.searchValue.isNullOrEmpty() && !listNewsRequest.langId.isNullOrEmpty()){
            val searchBy = listNewsRequest.searchBy
            val searchValue = listNewsRequest.searchValue.toLowerCase()
            sql = "select * from news where lower(langId) = ? and lower(${searchBy}) like '%${searchValue}%' order by newsId desc limit ${listNewsRequest.pageSize} offset $pageNumber"
            preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setString(1, listNewsRequest.langId)
        }else{
            sql = "select * from news where lower(langId) = ? order by newsId desc limit ${listNewsRequest.pageSize} offset $pageNumber"
            preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setString(1, listNewsRequest.langId)
        }

        return Flowable.fromCallable {
            try {
                val resultSet = preparedStatement.executeQuery()
                while(resultSet.next()){
                    result.add(
                        NewsItem(
                            newsId = resultSet.getInt("newsId"),
                            newsCategoryId = resultSet.getInt("newsCategoryId"),
                            newsTitle = resultSet.getString("newsTitle"),
                            newsMetaData = resultSet.getString("newsMetaData"),
                            newsShortContent = resultSet.getString("newsShortContent"),
                            newsLongContent = resultSet.getString("newsLongContent"),
                            newsAuthor = resultSet.getString("newsAuthor"),
                            slug = resultSet.getString("slug"),
                            langId = resultSet.getString("langId"),
                            active = resultSet.getString("active"),
                            imagePathUrl = resultSet.getString("imagePathUrl"),
                            createdAt = resultSet.getString("createdAt"),
                            userRekam = resultSet.getString("userRekam")
                        )
                    )
                }
                connection.close()
                result
            }catch (e: Exception){
                connection.close()
                result
            }
        }
    }

    override fun getNews(id: Int): Single<NewsItem> {
        val connection : Connection = dataSource.connection
        var result = NewsItem()
        return Single.fromCallable {
            try {
                val preparedStatement: PreparedStatement = connection.prepareStatement("select * from news where newsId=?")
                preparedStatement.setInt(1, id)
                val resultSet = preparedStatement.executeQuery()
                if(resultSet.next()){
                    result = NewsItem(
                        newsId = resultSet.getInt("newsId"),
                        newsCategoryId = resultSet.getInt("newsCategoryId"),
                        newsTitle = resultSet.getString("newsTitle"),
                        newsMetaData = resultSet.getString("newsMetaData"),
                        newsShortContent = resultSet.getString("newsShortContent"),
                        newsLongContent = resultSet.getString("newsLongContent"),
                        newsAuthor = resultSet.getString("newsAuthor"),
                        slug = resultSet.getString("slug"),
                        langId = resultSet.getString("langId"),
                        active = resultSet.getString("active"),
                        imagePathUrl = resultSet.getString("imagePathUrl"),
                        createdAt = resultSet.getString("createdAt"),
                        userRekam = resultSet.getString("userRekam")
                    )
                }
                connection.close()
                result
            }catch (e: Exception){
                connection.close()
                result
            }
        }
    }

    override fun insertNews(insertNewsRequest: InsertNewsRequest): Single<Boolean> {
        val connection : Connection = dataSource.connection
        return Single.fromCallable {
            try {
                val preparedStatement: PreparedStatement = connection.prepareStatement("insert into news(newsCategoryId, newsTitle, newsMetaData, newsShortContent, newsLongContent, newsAuthor, imagePathUrl, slug, langId, status, userRekam) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")
                preparedStatement.setString(1, insertNewsRequest.newsCategoryId)
                preparedStatement.setString(2, insertNewsRequest.newsTitle)
                preparedStatement.setString(3, insertNewsRequest.newsMetaData)
                preparedStatement.setString(4, insertNewsRequest.newsShortContent)
                preparedStatement.setString(5, insertNewsRequest.newsLongContent)
                preparedStatement.setString(6, insertNewsRequest.newsAuthor)
                preparedStatement.setString(7, insertNewsRequest.imagePathUrl)
                preparedStatement.setString(8, insertNewsRequest.newsTitle?.toSlug())
                preparedStatement.setString(9, insertNewsRequest.langId)
                preparedStatement.setString(10, insertNewsRequest.active)
                preparedStatement.setString(11, insertNewsRequest.userRekam)
                preparedStatement.execute()
                connection.close()
                true
            }catch (e: Exception){
                connection.close()
                false
            }
        }
    }

    override fun updateNews(updateNewsRequest: UpdateNewsRequest): Single<Boolean> {
        val connection : Connection = dataSource.connection
        return Single.fromCallable {
            try {
                val preparedStatement: PreparedStatement = connection.prepareStatement("update news set newsCategoryId = ?, newsTitle=?, newsMetaData=?, newsShortContent=?, newsLongContent=?, newsAuthor=?, imagePathUrl=?, slug=?, langId=?, status=?,  updatedAt=sysdate(), userUbah=? where newsId=?")
                preparedStatement.setString(1, updateNewsRequest.newsCategoryId)
                preparedStatement.setString(2, updateNewsRequest.newsTitle)
                preparedStatement.setString(3, updateNewsRequest.newsMetaData)
                preparedStatement.setString(4, updateNewsRequest.newsShortContent)
                preparedStatement.setString(5, updateNewsRequest.newsLongContent)
                preparedStatement.setString(6, updateNewsRequest.newsAuthor)
                preparedStatement.setString(7, updateNewsRequest.imagePathUrl)
                preparedStatement.setString(8, updateNewsRequest.newsTitle?.toSlug())
                preparedStatement.setString(9, updateNewsRequest.langId)
                preparedStatement.setString(10, updateNewsRequest.active)
                preparedStatement.setString(11, updateNewsRequest.userUbah)
                updateNewsRequest.newsId?.let { preparedStatement.setInt(12, it) }
                preparedStatement.execute()
                connection.close()
                true
            }catch (e: Exception){
                connection.close()
                false
            }
        }
    }

    override fun deleteNews(id: Int): Single<Boolean> {
        val connection : Connection = dataSource.connection
        return Single.fromCallable {
            try {
                val preparedStatement: PreparedStatement = connection.prepareStatement("delete from news where newsId = ?")
                preparedStatement.setInt(1, id)
                preparedStatement.execute()
                connection.close()
                true
            }catch (e: Exception){
                connection.close()
                false
            }
        }
    }

}