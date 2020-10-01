package data.user.db

import com.zaxxer.hikari.HikariDataSource
import data.news.model.request.UpdateNewsRequest
import data.user.model.UserItem
import data.user.model.request.InsertUserRequest
import data.user.model.request.ListUserRequest
import data.user.model.request.UpdateUserRequest
import io.reactivex.Flowable
import io.reactivex.Single
import java.sql.Connection
import java.sql.PreparedStatement

class UserDatabaseImpl(private val dataSource: HikariDataSource): UserDatabase {

    override fun listUser(listUserRequest: ListUserRequest): Flowable<List<UserItem>> {
        val pageNumber: Int = if (listUserRequest.pageNumber.equals("1")) {
            0
        } else {
            listUserRequest.pageNumber?.toInt()?.minus(1) ?: 0
        }

        val sql: String = if (!listUserRequest.searchBy.isNullOrEmpty()) {
            val searchBy = listUserRequest.searchBy
            val searchValue = listUserRequest.searchBy.toLowerCase()
            "select * from users where lower(${searchBy}) like %${searchValue}% order by id desc limit ${listUserRequest.pageSize} offset $pageNumber"
        } else {
            "select * from users  order by id desc limit ${listUserRequest.pageSize} offset $pageNumber"
        }

        val connection : Connection = dataSource.connection
        val result: MutableList<UserItem> = arrayListOf()
        return Flowable.fromCallable {
            try {
                val preparedStatement: PreparedStatement = connection.prepareStatement(sql)
                val resultSet = preparedStatement.executeQuery()
                while(resultSet.next()){
                    result.add(
                        UserItem(
                            id = resultSet.getInt("id"),
                            email = resultSet.getString("email"),
                            password = resultSet.getString("password"),
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

    override fun getUser(userId: Int): Single<UserItem> {
        val connection : Connection = dataSource.connection
        val result = UserItem()
        return Single.fromCallable {
            try {
                val preparedStatement: PreparedStatement = connection.prepareStatement("select * from users where id=?")
                preparedStatement.setInt(1, userId)
                val resultSet = preparedStatement.executeQuery()
                if(resultSet.next()){
                    with(result){
                        id = resultSet.getInt("id")
                        email = resultSet.getString("email")
                        password = resultSet.getString("password")
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

    override fun insertUser(insertUserRequest: InsertUserRequest): Single<Boolean> {
        val connection : Connection = dataSource.connection
        return Single.fromCallable {
            try {
                val preparedStatement: PreparedStatement = connection.prepareStatement("insert into users(email, password, userRekam) values(?, ?, ?)")
                preparedStatement.setString(1, insertUserRequest.email)
                preparedStatement.setString(2, insertUserRequest.password)
                preparedStatement.setString(3, insertUserRequest.userRekam)
                preparedStatement.execute()
                connection.close()
                true
            }catch (e: Exception){
                connection.close()
                false
            }
        }
    }

    override fun updateUser(updateUserRequest: UpdateUserRequest): Single<Boolean> {
        val connection : Connection = dataSource.connection
        return Single.fromCallable {
            try {
                val preparedStatement: PreparedStatement = connection.prepareStatement("update users set email=?, password=?, userUbah=? where userId=?")
                preparedStatement.setString(1, updateUserRequest.email)
                preparedStatement.setString(2, updateUserRequest.password)
                preparedStatement.setString(3, updateUserRequest.userUbah)
                updateUserRequest.id?.let { preparedStatement.setInt(4, it) }
                preparedStatement.execute()
                connection.close()
                true
            }catch (e: Exception){
                connection.close()
                false
            }
        }
    }

    override fun deleteUser(userId: Int): Single<Boolean> {
        val connection : Connection = dataSource.connection
        return Single.fromCallable {
            try {
                val preparedStatement: PreparedStatement = connection.prepareStatement("delete from users where id = ?")
                preparedStatement.setInt(1, userId)
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