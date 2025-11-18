package com.hackheros.domain.repository

import com.hackheros.database.Categories
import com.hackheros.database.DatabaseFactory.dbQuery
import com.hackheros.database.Lessons
import com.hackheros.domain.model.CategoryResponse
import com.hackheros.domain.model.LessonReponse
import org.jetbrains.exposed.sql.selectAll


class CategoryRepository{

    suspend fun getAllCategories():List<CategoryResponse> = dbQuery{
        Categories
            .selectAll()
            .map{row -> CategoryResponse(
                id = row[Categories.id].value,
                name=row[Categories.name],
                description=row[Categories.description]
            )}
    }




}