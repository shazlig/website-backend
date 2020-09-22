package data.libs.db

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource

object MysqlClientDb {

     fun hikariDataSourceMysql(): HikariDataSource {
        val config = HikariConfig()

        with(config){
           jdbcUrl = "jdbc:mysql://172.28.208.22:3306/website_new?useUnicode=true&serverTimezone=Asia/Jakarta"
           username = "root"
           password = "welcome1"
           driverClassName = "com.mysql.cj.jdbc.Driver"
           isAutoCommit = true
           poolName = "website_new"
           connectionTimeout = 30000
           maximumPoolSize = 20
           minimumIdle = 1
           maxLifetime = 50000
           idleTimeout = 30000
           addDataSourceProperty("cachePrepStmts", "true")
           addDataSourceProperty("prepStmtCacheSize", "250")
           addDataSourceProperty("prepStmtCacheSqlLimit", "2048")
           validate()
        }
        return HikariDataSource(config)
    }
}