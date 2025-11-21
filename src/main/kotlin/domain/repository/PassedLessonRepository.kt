package com.hackheros.domain.repository


import com.hackheros.database.PassedLessons
import com.hackheros.database.DatabaseFactory.dbQuery
import com.hackheros.domain.model.PassedLessonRequest
import com.hackheros.domain.model.PassedLessonResponse
import org.jetbrains.exposed.sql.*
import java.time.Instant

private const val DEMO_USER_ID = 1

class PassedLessonRepository {

    private fun rowToPassed(row: ResultRow) = PassedLessonResponse(
        id = row[PassedLessons.id].value,
        userId = row[PassedLessons.userId].value,
        lessonId = row[PassedLessons.lessonId].value,
        completedAt = row[PassedLessons.completedAt].toString(),
        correctAnswers = row[PassedLessons.correctAnswers]
    )

    suspend fun addForDemoUser(req: PassedLessonRequest) = dbQuery {
        PassedLessons.insert {
            it[userId] = DEMO_USER_ID
            it[lessonId] = req.lessonId
            it[completedAt] = Instant.now()
            it[correctAnswers] = req.correctAnswers
        }
    }

    suspend fun getForDemoUser(): List<PassedLessonResponse> = dbQuery {
        PassedLessons
            .selectAll().where { PassedLessons.userId eq DEMO_USER_ID }
            .orderBy(PassedLessons.completedAt, SortOrder.DESC)
            .map(::rowToPassed)
    }
}
