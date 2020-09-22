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
        var pageNumber: Int = if (listNewsRequest.pageNumber.equals("1")) {
            0
        } else {
            listNewsRequest.pageNumber?.toInt()?.minus(1) ?: 0
        }

        var sql: String = if (!listNewsRequest.searchBy.isNullOrEmpty()) {
            val searchBy = listNewsRequest.searchBy
            val searchValue = listNewsRequest.searchBy.toLowerCase()
            "select * from news where lower(${searchBy}) like %${searchValue}% order by id desc limit ${listNewsRequest.pageSize} offset $pageNumber"
        } else {
            "select * from news  order by id desc limit ${listNewsRequest.pageSize} offset $pageNumber"
        }

        val connection : Connection = dataSource.connection
        var result: MutableList<NewsItem> = arrayListOf()
        return Flowable.fromCallable {
            try {
                val preparedStatement: PreparedStatement = connection.prepareStatement(sql)
                val resultSet = preparedStatement.executeQuery()
                while(resultSet.next()){
                    result.add(
                        NewsItem(
                            id = resultSet.getInt("id"),
                            title = resultSet.getString("title"),
                            keywords = resultSet.getString("keywords"),
                            descriptionContent = resultSet.getString("descriptionContent"),
                            content = resultSet.getString("content"),
                            imageUrl = resultSet.getString("imageUrl"),
                            slug = resultSet.getString("slug"),
                            createdAt = resultSet.getString("createdAt"),
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

    override fun getNews(newsId: Int): Single<NewsItem> {
        val connection : Connection = dataSource.connection
        var result = NewsItem()
        return Single.fromCallable {
            try {
                val preparedStatement: PreparedStatement = connection.prepareStatement("select * from news where id=?")
                preparedStatement.setInt(1, newsId)
                val resultSet = preparedStatement.executeQuery()
                if(resultSet.next()){
                    with(result){
                        id = resultSet.getInt("id")
                        title = resultSet.getString("title")
                        keywords = resultSet.getString("keywords")
                        descriptionContent = resultSet.getString("descriptionContent")
                        content = resultSet.getString("content")
                        imageUrl = resultSet.getString("imageUrl")
                        slug = resultSet.getString("slug")
                        createdAt = resultSet.getString("createdAt")
                    }
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
                val preparedStatement: PreparedStatement = connection.prepareStatement("insert into news(title, keywords, descriptionContent, content, imageUrl, slug, userRekam) values(?, ?, ?, ?, ?, ?, ?)")
                preparedStatement.setString(1, insertNewsRequest.title)
                preparedStatement.setString(2, insertNewsRequest.keywords)
                preparedStatement.setString(3, insertNewsRequest.descriptionContent)
                preparedStatement.setString(4, insertNewsRequest.content)
                preparedStatement.setString(5, insertNewsRequest.imageUrl)
                preparedStatement.setString(6, insertNewsRequest.title?.toSlug())
                preparedStatement.setString(7, insertNewsRequest.userRekam)
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
                val preparedStatement: PreparedStatement = connection.prepareStatement("update news set title=?, keywords=?, descriptionContent=?, content=?, imageUrl=?, slug=?, userUbah=?, updatedAt=sysdate() where id=?")
                preparedStatement.setString(1, updateNewsRequest.title)
                preparedStatement.setString(2, updateNewsRequest.keywords)
                preparedStatement.setString(3, updateNewsRequest.descriptionContent)
                preparedStatement.setString(4, updateNewsRequest.content)
                preparedStatement.setString(5, updateNewsRequest.imageUrl)
                preparedStatement.setString(6, updateNewsRequest.title?.toSlug())
                preparedStatement.setString(7, updateNewsRequest.userUbah)
                preparedStatement.setInt(8, updateNewsRequest.id)
                preparedStatement.execute()
                connection.close()
                true
            }catch (e: Exception){
                connection.close()
                false
            }
        }
    }

    override fun deleteNews(newsId: Int): Single<Boolean> {
        val connection : Connection = dataSource.connection
        return Single.fromCallable {
            try {
                val preparedStatement: PreparedStatement = connection.prepareStatement("delete from news where id = ?")
                preparedStatement.setInt(1, newsId)
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