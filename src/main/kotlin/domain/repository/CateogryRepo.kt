package com.hackheros.domain.repository

import com.hackheros.database.Categories
import com.hackheros.database.DatabaseFactory.dbQuery
import com.hackheros.domain.model.CategoryRequest
import com.hackheros.domain.model.CategoryResponse
import org.jetbrains.exposed.sql.*

class CategoryRepository {

    private fun rowToCategory(row: ResultRow) = CategoryResponse(
        id = row[Categories.id].value,
        name = row[Categories.name],
        description = row[Categories.description]
    )

    suspend fun getAll(): List<CategoryResponse> = dbQuery {
        Categories.selectAll().map(::rowToCategory)
    }

    suspend fun addCategory(req: CategoryRequest) = dbQuery {
        Categories.insert {
            it[name] = req.name
            it[description] = req.description
        }
    }
}
