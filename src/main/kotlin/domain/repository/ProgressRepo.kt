package com.hackheros.domain.repository

import com.hackheros.database.PassedLessons
import com.hackheros.database.Users
import com.hackheros.database.DatabaseFactory.dbQuery
import com.hackheros.database.Lessons
import com.hackheros.domain.model.*
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.*
import java.time.Instant

class ProgressRepository(
    private val userRepository: UserRepository = UserRepository()
) {


    suspend fun saveCompletedLesson(
        userId: Int,
        request: CompleteLessonRequest
    ): PassedLessonResponse {

        val now =  Instant.now()

        val id = dbQuery {
            PassedLessons.insertAndGetId { row ->
                row[PassedLessons.userId] = EntityID(userId, Users)
                row[PassedLessons.lessonId] = EntityID(request.lessonId, Lessons)
                row[PassedLessons.correctAnswers] = request.correctAnswers
                row[PassedLessons.completedAt] = now
            }.value
        }


        val expDelta = request.correctAnswers * 10

        userRepository.updateExperienceAndStreak(
            userId = userId,
            expDelta = expDelta,
            newStreak = null
        )

        return PassedLessonResponse(
            id = id,
            lessonId = request.lessonId,
            correctAnswers = request.correctAnswers,
            completedAt = now.toString()
        )
    }


    suspend fun getUserProgress(userId: Int): UserProgressResponse = dbQuery {

        val userRow = Users
            .selectAll().where { Users.id eq userId }
            .single()

        val completed = PassedLessons
            .selectAll().where { PassedLessons.userId eq userId }
            .orderBy(PassedLessons.completedAt to SortOrder.DESC)
            .map { row ->
                CompletedLessonSummary(
                    lessonId = row[PassedLessons.lessonId].value,
                    correctAnswers = row[PassedLessons.correctAnswers],
                    completedAt = row[PassedLessons.completedAt].toString()
                )
            }

        UserProgressResponse(
            experience = userRow[Users.experience],
            streak = userRow[Users.streak],
            completedLessons = completed
        )
    }
}
