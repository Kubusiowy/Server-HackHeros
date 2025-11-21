package com.hackheros.domain.repository

import com.hackheros.database.Lessons
import com.hackheros.database.DatabaseFactory.dbQuery
import com.hackheros.domain.model.LessonRequest
import com.hackheros.domain.model.LessonResponse
import org.jetbrains.exposed.sql.*

class LessonRepository {

    private fun rowToLesson(row: ResultRow) = LessonResponse(
        id = row[Lessons.id].value,
        name = row[Lessons.name],
        description = row[Lessons.description],
        categoryId = row[Lessons.categoryId].value
    )

    suspend fun getForCategory(categoryId: Int): List<LessonResponse> = dbQuery {
        Lessons
            .selectAll().where { Lessons.categoryId eq categoryId }
            .map(::rowToLesson)
    }

    suspend fun addForCategory(req: LessonRequest, categoryId: Int) = dbQuery {
        Lessons.insert {
            it[name] = req.name
            it[description] = req.description
            it[Lessons.categoryId] = categoryId
        }
    }
}
