package data.user.db

import com.zaxxer.hikari.HikariDataSource
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
            "select * from users where lower(${searchBy}) like %${searchValue}% order by userId desc limit ${listUserRequest.pageSize} offset $pageNumber"
        } else {
            "select * from users  order by userId desc limit ${listUserRequest.pageSize} offset $pageNumber"
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
                            userId = resultSet.getInt("userId"),
                            username = resultSet.getString("username"),
                            password = resultSet.getString("password"),
                            fullName = resultSet.getString("fullName"),
                            userType = resultSet.getString("userType"),
                            active = resultSet.getString("active"),
                            userRekam = resultSet.getString("userRekam"),
                            createdAt = resultSet.getString("createdAt")
                        )
                    )
                }
                connection.close()
                println(result)
                result
            }catch (e: Exception){
                connection.close()
                result
            }
        }
    }

    override fun getUser(id: Int): Single<UserItem> {
        val connection : Connection = dataSource.connection
        var result = UserItem()
        return Single.fromCallable {
            try {
                val preparedStatement: PreparedStatement = connection.prepareStatement("select * from users where userId=?")
                preparedStatement.setInt(1, id)
                val resultSet = preparedStatement.executeQuery()
                if(resultSet.next()){
                    result = UserItem(
                        userId = resultSet.getInt("userId"),
                        username = resultSet.getString("username"),
                        password = resultSet.getString("password"),
                        fullName = resultSet.getString("fullName"),
                        userType = resultSet.getString("userType"),
                        active = resultSet.getString("active"),
                        userRekam = resultSet.getString("userRekam"),
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

    override fun getUserByEmail(email: String): Single<Boolean> {
        val connection : Connection = dataSource.connection
        var result = false
        return Single.fromCallable {
            try {
                val preparedStatement: PreparedStatement = connection.prepareStatement("select count(1) rowCount from users where username=?")
                preparedStatement.setString(1, email)
                val resultSet = preparedStatement.executeQuery()
                resultSet.next()
                result = resultSet.getInt("rowCount") > 0
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
                val preparedStatement: PreparedStatement = connection.prepareStatement("insert into users(username, password, userType, fullName, userRekam, active) values(?, ?, ?, ?, ?, ?)")
                preparedStatement.setString(1, insertUserRequest.username)
                preparedStatement.setString(2, insertUserRequest.password)
                preparedStatement.setString(3, insertUserRequest.userType)
                preparedStatement.setString(4, insertUserRequest.fullName)
                preparedStatement.setString(5, insertUserRequest.userRekam)
                preparedStatement.setString(6, insertUserRequest.active)
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
                val preparedStatement: PreparedStatement = connection.prepareStatement("update users set username=?, password=?, userType=?, fullName=?, userUbah=? where userId=?")
                preparedStatement.setString(1, updateUserRequest.username)
                preparedStatement.setString(2, updateUserRequest.password)
                preparedStatement.setString(3, updateUserRequest.userType)
                preparedStatement.setString(4, updateUserRequest.fullName)
                preparedStatement.setString(5, updateUserRequest.userUbah)
                updateUserRequest.userId?.let { preparedStatement.setInt(6, it) }
                preparedStatement.execute()
                connection.close()
                true
            }catch (e: Exception){
                connection.close()
                false
            }
        }
    }

    override fun deleteUser(id: Int): Single<Boolean> {
        val connection : Connection = dataSource.connection
        return Single.fromCallable {
            try {
                val preparedStatement: PreparedStatement = connection.prepareStatement("delete from users where userId = ?")
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