package com.hackheros.database

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {

    fun init(){
        val config = HikariConfig().apply {
            jdbcUrl = "jdbc:mysql://localhost:8889/HackHeros" //port zostaje 8889 na macu,   a na windowsie 3306
            driverClassName = "com.mysql.cj.jdbc.Driver"
            username = "root"
            password = "" //xampp(widnows) brak hasla ; macOs (mamp) root
            maximumPoolSize = 5
            isAutoCommit = false
            transactionIsolation = "TRANSACTION_REPEATABLE_READ"
            validate()
        }

        val dataSource = HikariDataSource(config)
        Database.connect(dataSource)
    }

    suspend fun <T> dbQuery(block : () -> T) : T = withContext(Dispatchers.IO) {
        transaction { block() }
    }
}