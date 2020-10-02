package data.category.db

import com.zaxxer.hikari.HikariDataSource
import data.category.model.CategoryItem
import data.category.model.request.InsertCategoryRequest
import data.category.model.request.ListCategoryRequest
import data.category.model.request.UpdateCategoryRequest
import io.reactivex.Flowable
import io.reactivex.Single
import utils.toSlug
import java.sql.Connection
import java.sql.PreparedStatement

class CategoryDatabaseImpl(private val dataSource: HikariDataSource): CategoryDatabase {

    override fun listCategory(listCategoryRequest: ListCategoryRequest): Flowable<List<CategoryItem>> {
        val connection : Connection = dataSource.connection
        val result: MutableList<CategoryItem> = arrayListOf()
        val pageNumber: Int = if (listCategoryRequest.pageNumber.equals("1")) {
            0
        } else {
            listCategoryRequest.pageNumber?.toInt()?.minus(1) ?: 0
        }

        val sql: String
        val preparedStatement: PreparedStatement

        if (!listCategoryRequest.searchBy.isNullOrEmpty() && !listCategoryRequest.searchValue.isNullOrEmpty() && !listCategoryRequest.langId.isNullOrEmpty()){
            val searchBy = listCategoryRequest.searchBy
            val searchValue = listCategoryRequest.searchValue.toLowerCase()
            sql = "select * from category where lower(langId) = ? and lower(${searchBy}) like '%${searchValue}%' order by categoryId desc limit ${listCategoryRequest.pageSize} offset $pageNumber"
            preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setString(1, listCategoryRequest.langId)
        }else{
            sql = "select * from category where lower(langId) = ? order by categoryId desc limit ${listCategoryRequest.pageSize} offset $pageNumber"
            preparedStatement = connection.prepareStatement(sql)
            preparedStatement.setString(1, listCategoryRequest.langId)
        }

        return Flowable.fromCallable {
            try {
                val resultSet = preparedStatement.executeQuery()
                while(resultSet.next()){
                    result.add(
                        CategoryItem(
                            categoryId = resultSet.getInt("categoryId"),
                            categoryType = resultSet.getString("categoryType"),
                            categoryTitle = resultSet.getString("categoryTitle"),
                            categoryShortDescription = resultSet.getString("categoryShortDescription"),
                            categoryLongDescription = resultSet.getString("categoryLongDescription"),
                            active = resultSet.getString("active"),
                            slug = resultSet.getString("slug"),
                            langId = resultSet.getString("langId"),
                            createdAt = resultSet.getString("createdAt")
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

    override fun getCategory(id: Int): Single<CategoryItem> {
        val connection : Connection = dataSource.connection
        var result = CategoryItem()
        return Single.fromCallable {
            try {
                val preparedStatement: PreparedStatement = connection.prepareStatement("select * from category where categoryId=?")
                preparedStatement.setInt(1, id)
                val resultSet = preparedStatement.executeQuery()
                if(resultSet.next()){
                    result = CategoryItem(
                        categoryId = resultSet.getInt("categoryId"),
                        categoryType = resultSet.getString("categoryType"),
                        categoryTitle = resultSet.getString("categoryTitle"),
                        categoryShortDescription = resultSet.getString("categoryShortDescription"),
                        categoryLongDescription = resultSet.getString("categoryLongDescription"),
                        active = resultSet.getString("active"),
                        slug = resultSet.getString("slug"),
                        langId = resultSet.getString("langId"),
                        createdAt = resultSet.getString("createdAt")
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

    override fun insertCategory(insertCategoryRequest: InsertCategoryRequest): Single<Boolean> {
        val connection : Connection = dataSource.connection
        return Single.fromCallable {
            try {
                val preparedStatement: PreparedStatement = connection.prepareStatement("insert into category(categoryType, categoryTitle, categoryShortDescription, categoryLongDescription, active, slug, langId, userRekam) values(?, ?, ?, ?, ?, ?, ?, ?)")
                preparedStatement.setString(1, insertCategoryRequest.categoryType)
                preparedStatement.setString(2, insertCategoryRequest.categoryTitle)
                preparedStatement.setString(3, insertCategoryRequest.categoryShortDescription)
                preparedStatement.setString(4, insertCategoryRequest.categoryLongDescription)
                preparedStatement.setString(5, insertCategoryRequest.active)
                preparedStatement.setString(6, insertCategoryRequest.categoryTitle?.toSlug())
                preparedStatement.setString(7, insertCategoryRequest.langId)
                preparedStatement.setString(8, insertCategoryRequest.userRekam)
                preparedStatement.execute()
                connection.close()
                true
            }catch (e: Exception){
                connection.close()
                false
            }
        }
    }

    override fun updateCategory(updateCategoryRequest: UpdateCategoryRequest): Single<Boolean> {
        val connection : Connection = dataSource.connection
        return Single.fromCallable {
            try {
                val preparedStatement: PreparedStatement = connection.prepareStatement("update category set categoryType=?, categoryTitle=?, categoryShortDescription=?, categoryLongDescription=?, active=?, slug=?, langId=?, userUbah=?  where categoryId=?")
                preparedStatement.setString(1, updateCategoryRequest.categoryType)
                preparedStatement.setString(2, updateCategoryRequest.categoryTitle)
                preparedStatement.setString(3, updateCategoryRequest.categoryShortDescription)
                preparedStatement.setString(4, updateCategoryRequest.categoryLongDescription)
                preparedStatement.setString(5, updateCategoryRequest.active)
                preparedStatement.setString(6, updateCategoryRequest.categoryTitle?.toSlug())
                preparedStatement.setString(7, updateCategoryRequest.langId)
                preparedStatement.setString(8, updateCategoryRequest.userUbah)
                updateCategoryRequest.categoryId?.let { preparedStatement.setInt(9, it) }
                preparedStatement.execute()
                connection.close()
                true
            }catch (e: Exception){
                println(e)
                connection.close()
                false
            }
        }
    }

    override fun deleteCategory(id: Int): Single<Boolean> {
        val connection : Connection = dataSource.connection
        return Single.fromCallable {
            try {
                val preparedStatement: PreparedStatement = connection.prepareStatement("delete from category where categoryId = ?")
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