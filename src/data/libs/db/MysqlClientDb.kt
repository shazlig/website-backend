package data.libs.db

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource

object MysqlClientDb {

     fun hikariDataSourceMysql(): HikariDataSource {
        val config = HikariConfig()

        with(config){
           jdbcUrl = System.getenv("WEBSITE_DB_JDBCURL")
           username = System.getenv("WEBSITE_DB_USERNAME")
           password = System.getenv("WEBSITE_DB_PASSWORD")
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