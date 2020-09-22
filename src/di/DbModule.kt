package di

import data.libs.db.MysqlClientDb.hikariDataSourceMysql
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dbModule = module(createdAtStart = true){
    single(named("mysql")){
        hikariDataSourceMysql()
    }

}
